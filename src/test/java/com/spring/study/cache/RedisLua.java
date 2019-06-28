package com.spring.study.cache;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by zmm on 2019-03-06
 */
public class RedisLua {

    private static String host = "10.10.11.238";
    private static Integer port = 6379;


    public static String buildLuaScript() {
        StringBuilder lua = new StringBuilder();
        lua.append("local c");
        lua.append("\nc = redis.call('get',KEYS[1])");
        // 调用不超过最大值，则直接返回
        lua.append("\nif c and tonumber(c) > tonumber(ARGV[1]) then");
        lua.append("\nreturn c;");
        lua.append("\nend");
        // 执行计算器自加
        lua.append("\nc = redis.call('incr',KEYS[1])");
        lua.append("\nif tonumber(c) == 1 then");
        // 从第一次调用开始限流，设置对应键值的过期
        lua.append("\nredis.call('expire',KEYS[1],ARGV[2])");
        lua.append("\nend");
        lua.append("\nreturn c;");
        return lua.toString();
    }

    public static Object eve(Jedis jedis, List<String> keys, List<String> args, String script) {
        return jedis.evalsha(script, keys, args);
    }

    public static void incr(Jedis jedis, String keys) {
        System.out.println(jedis.incr(keys));
    }

    public static void del(Jedis jedis, String keys) {
        System.out.println(jedis.del(keys));
    }


    public static void main(String[] args) {
        Jedis jedis = new Jedis(host, port);
        jedis.auth("SjhkHD3J5k6H8SjSbK3SC");
        // df:a60fd2fb34dd3f07
//        String script = "local num = redis.call('incr', KEYS[1])\n" +
//                "if tonumber(num) == 1 then\n" +
//                "  redis.call('expire', KEYS[1], ARGV[1])\n" +
//                "  return 1\n" +
//                "elseif tonumber(num) > tonumber(ARGV[2]) then\n" +
//                "  return 0\n" +
//                "else \n" +
//                "  return 1\n" +
//                "end\n";

        String script = "local num = redis.call('lrange', KEYS[1], ARGV[1], ARGV[2]) \n" +
                " for k,v in pairs(num) do \n" +
                "    local strList = {} \n" +
                "    string.gsub(v,'[^_]+', function(w)table.insert(strList, w)end) \n" +
                "    if strList[1] == ARGV[3] and strList[4] ~= ARGV[4] then \n" +
                "        return 1\n" +
                "    end \n" +
                " end \n" +
                " return 0";

//    del(jedis, "localhost");

//        jedis.lpush("df:a60fd2fb00dd3f07", "10021_0_1555983478_2");
        System.out.println(jedis.lrange("df:11", 0, 100));
//        jedis.del("df:11");
//        System.out.println(eve(jedis, Arrays.asList("df:a60fd2fb34dd3f07"), Arrays.asList("0", "100", "10021", "3"), jedis.scriptLoad(script)));
    }


}

package org.spring.cloud.order.service.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangqj on 2017/4/30.
 * @param <T>
 */
@Configuration
@EnableCaching
public class RedisConfig<T> extends CachingConfigurerSupport {
	@Value("${spring.redis.cluster.nodes}")
	private String clusterNodes;

	@Value("${spring.redis.cluster.port}")
	private int port;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.pool.max-idle}")
	private int maxIdle;

	@Value("${spring.redis.pool.max-wait}")
	private long maxWaitMillis;

	/**
	 * 生产key的策略
	 *
	 * @return
	 */

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {

			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};

	}

	/**
	 * 管理缓存
	 *
	 * @param redisTemplate
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	@Bean
	public CacheManager CacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
		// 设置cache过期时间,时间单位是秒
		rcm.setDefaultExpiration(60);
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("test", 60L);
		rcm.setExpires(map);
		return rcm;
	}

	@Autowired
	RedisProperties clusterProperties;
	
	/**
	 * redis 数据库连接池
	 * 
	 * @return
	 */
	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
//		String ipArray = clusterProperties.getNodes().toString(); //获取当前ip
//		if(ipArray.contains(",")) {
//			Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//			Collection<String> con = new ArrayList<String>();
//			Set<RedisNode> nodes = Sets.newHashSetWithExpectedSize(ipArray.length());
//			String[] ips = ipArray.substring(1, ipArray.length()-1).trim().split(",");
//			for(String ip:ips) {
//				String[] strs = ip.split(":");
//				nodes.add(new RedisNode(strs[0].trim(),Integer.valueOf(strs[1])));
//			}
//			RedisClusterConfiguration rc = new RedisClusterConfiguration();
//			rc.setClusterNodes(nodes);
//			return new JedisConnectionFactory(rc);
//		}else {
			JedisConnectionFactory factory = new JedisConnectionFactory();
//			String[] ipOrPort = ipArray.split(":");
			factory.setHostName(clusterNodes);
			factory.setPort(port);
			factory.setTimeout(timeout); // 设置连接超时时间
			return factory;
//		}
	}

	/**
	 * redisTemplate配置
	 *
	 * @param factory
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

}

package cn.demo.gateway.ratelimiter;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.AbstractRateLimiter;
import org.springframework.cloud.gateway.support.ConfigurationService;
import org.springframework.core.style.ToStringCreator;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Min;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class GuavaRateLimiter extends AbstractRateLimiter<GuavaRateLimiter.Config> {
    private Config defaultConfig;
    int defaultReplenishRate = 1;
    int defaultBurstCapacity = 1;

    private final RateLimiter rateLimiter = RateLimiter.create(1);
    public static final String CONFIGURATION_PROPERTY_NAME = "resilience4j-rate-limiter";


    public GuavaRateLimiter() {
        super(Config.class, CONFIGURATION_PROPERTY_NAME, (ConfigurationService) null);
        this.defaultConfig = new GuavaRateLimiter.Config().setReplenishRate(defaultReplenishRate).setBurstCapacity(defaultBurstCapacity);
    }


    //    public Resilience4jLimiter(Class<Config> configClass, String configurationPropertyName, ConfigurationService configurationService) {
//        super(configClass, configurationPropertyName, configurationService);
//    }
//    public Resilience4jLimiter(int defaultReplenishRate, int defaultBurstCapacity) {
//        super(Resilience4jLimiter.Config.class, CONFIGURATION_PROPERTY_NAME, (ConfigurationService) null);
//        this.defaultConfig = new Resilience4jLimiter.Config().setReplenishRate(defaultReplenishRate).setBurstCapacity(defaultBurstCapacity);
//    }


    @Override
    public Mono<Response> isAllowed(String routeId, String id) {
        return Mono.just(new Response(rateLimiter.tryAcquire(1,1, TimeUnit.MILLISECONDS), Maps.newHashMap()));
    }

    public static class Config {

        @Min(1)
        private int replenishRate;

        @Min(0)
        private int burstCapacity = 1;

        @Min(1)
        private int requestedTokens = 1;

        public int getReplenishRate() {
            return replenishRate;
        }

        public GuavaRateLimiter.Config setReplenishRate(int replenishRate) {
            this.replenishRate = replenishRate;
            return this;
        }

        public int getBurstCapacity() {
            return burstCapacity;
        }

        public GuavaRateLimiter.Config setBurstCapacity(int burstCapacity) {
            this.burstCapacity = burstCapacity;
            return this;
        }

        public int getRequestedTokens() {
            return requestedTokens;
        }

        public GuavaRateLimiter.Config setRequestedTokens(int requestedTokens) {
            this.requestedTokens = requestedTokens;
            return this;
        }

        @Override
        public String toString() {
            return new ToStringCreator(this).append("replenishRate", replenishRate)
                    .append("burstCapacity", burstCapacity).append("requestedTokens", requestedTokens).toString();

        }

    }


}

package cn.net.arven.home.config;

import cn.net.arven.common.constant.Constant;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author 宋建华
 * @date 2019-05-07 08:51
 **/
@Component
public class WebConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置server虚拟路径，handler为jsp中访问的目录，locations为files相对应的本地路径
        registry.addResourceHandler("/small/**").addResourceLocations("file://"+Constant.STATIC_SMALL_PATH);
        registry.addResourceHandler("/large/**").addResourceLocations("file://"+Constant.STATIC_LARGE_PATH);
        registry.addResourceHandler("/truth/**").addResourceLocations("file://"+Constant.STATIC_TRUTH_PATH);
    }

}

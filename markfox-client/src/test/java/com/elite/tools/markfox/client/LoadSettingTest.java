package com.elite.tools.markfox.client;

import com.elite.tools.markfox.common.settings.Settings;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Created by wjc133.
 * Date: 16/8/23
 * Time: 下午1:01
 */
public class LoadSettingTest {
    @Test
    public void testLoad() throws FileNotFoundException {
        URL resource = this.getClass().getClassLoader().getResource("conf/common.yml");
        Yaml yaml = new Yaml();
        Settings read = yaml.loadAs(new FileInputStream("/Users/wjc133/Web/common.yml"),Settings.class);
        assertEquals("imgchr.com", read.getPic().getWebsite());
        assertEquals(5, read.getPic().getTimeout());
//        assertEquals(true, read.getGeneral().isFirstBoot());
    }
}

package com.whc.processor;

import com.whc.common.constant.ConstantDataProvider;
import com.whc.log.BaseLog;
import com.whc.model.GithubInfo;
import com.whc.service.IBaseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by whc on 2017/6/28.
 */
@Service("githubProcessor")
public class GithubProcessor implements PageProcessor {

    @Autowired
    private IBaseLogService baseLogService;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());

        String name = page.getHtml().xpath("//h1[@class='public']/strong/a/text()").toString();
        if (name == null){
            page.setSkip(true);
        } else {
            String author = page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString();
            String readme = page.getHtml().xpath("//div[@id='readme']/tidyText()").toString();
            BaseLog<GithubInfo> baseLog = new BaseLog<GithubInfo>();
            baseLog.setCode(ConstantDataProvider.SUCCESS_CODE);
            baseLog.setData(new GithubInfo(author, name, readme));
            baseLog.setDomain(ConstantDataProvider.GITHUB_DOMAIN);
            baseLogService.createBaseLog(baseLog);
        }
    }

    public Site getSite() {
        return site;
    }
}

package ircclient.irc.session.consumer;

import ircclient.util.Constants;
import twitterapi.TwitterApi;

public class TwitterBot implements BotConsumer {

    TwitterApi twitterApi = null;

    public TwitterBot(TwitterApi twitterApi) {
        this.twitterApi = twitterApi;
    }

    @Override
    public boolean parseForCall(String str) {
        if ((str.toLowerCase().contains("twitter trends") ||
                str.toLowerCase().contains("top ten trends"))) {
            return true;
        }
        return false;
    }

    @Override
    public String validateInput(String str) {
        return null;
    }

    @Override
    public String makeApiCall(String str) {
        if (str != null) {
            twitterApi.authenticate();
            twitterApi.apiCall(Constants.TRENDS_API_URL);
            twitterApi.parseTopTenTwitterTrends();
            return "Twitter trends: " + twitterApi.getTrendsStr();
        }
        return null;
    }

    @Override
    public String parseJsonContents(String str) {
        return null;
    }

    @Override
    public String returnResult(String str) {
        return makeApiCall(str);
    }
}

package sg.edu.smu.sis.twittercrawler.cli;

import org.apache.commons.cli.*;
import sg.edu.smu.sis.twittercrawler.task.AbstractTask;
import sg.edu.smu.sis.twittercrawler.task.CrawlUserProfileTask;
import sg.edu.smu.sis.twittercrawler.task.CrawlFolloweeTask;
import sg.edu.smu.sis.twittercrawler.task.CrawlUserTweetsTask;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taosun
 * @since 29/11/14
 */
public class Main {

    private static final String PROFILE = "profile";
    private static final String TWEETS = "tweets";
    private static final String FOLLOWERS = "followers";
    private static final String FOLLOWEES = "followees";

    private static final Options options;

    static {
        options = new Options();
        options.addOption(OptionBuilder.withLongOpt(PROFILE).withDescription("crawl user profile").create());
        options.addOption(OptionBuilder.withLongOpt(TWEETS).withDescription("crawl user tweets").create());
        options.addOption(OptionBuilder.withLongOpt(FOLLOWERS).withDescription("crawl user followers").create());
        options.addOption(OptionBuilder.withLongOpt(FOLLOWEES).withDescription("crawl user followees").create());
    }

    public static void main(String[] args) {
        List<AbstractTask> tasks = createTasks(args);
    }

    private static List<AbstractTask> createTasks(String[] args) {
        List<AbstractTask> tasks = new ArrayList<>();
        try {
            CommandLineParser parser = new BasicParser();
            CommandLine line = parser.parse(options, args);
            if (line.hasOption(PROFILE)) {
                tasks.add(new CrawlUserProfileTask());
            }
            if (line.hasOption(TWEETS)) {
                tasks.add(new CrawlUserTweetsTask());
            }
            if (line.hasOption(FOLLOWEES)) {
                tasks.add(new CrawlFolloweeTask());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return tasks;
    }

}
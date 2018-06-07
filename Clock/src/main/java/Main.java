import clock.Clock;
import clock.SetableClock;
import statistic.EventsStatistic;
import statistic.EventsStatisticImpl;

import java.time.Instant;

public class Main {

    public static void main(String[] args) {
        Clock clock = new SetableClock(Instant.now());
        EventsStatistic eventsStatistic = new EventsStatisticImpl(clock);

        eventsStatistic.incEvent("a");
        eventsStatistic.incEvent("a");
        eventsStatistic.incEvent("a");
        eventsStatistic.incEvent("a");
        eventsStatistic.incEvent("a");
        eventsStatistic.incEvent("b");
        eventsStatistic.incEvent("b");
        eventsStatistic.incEvent("b");
//        ((clock.SetableClock) clock).setNow(Instant.now().plus(Duration.ofHours(1)).plus(Duration.ofMinutes(1)));
        eventsStatistic.printStatistic();
    }
}

package util;

public class TimeFormatter
{
    public static String format(long seconds)
    {
        if (seconds < 60)
        {
            return (Long.toString(seconds));
        }
        else if(seconds < 3600)
        {
            return (Long.toString(seconds/60) + ":" + Long.toString(seconds));
        }

        return (Long.toString(seconds/3600) + ":" + Long.toString(seconds/60) + ":" + Long.toString(seconds));
    }
}

package util;

public class TimeFormatter
{
    public static String format(long seconds)
    {
        long hours = seconds / 3600;
        long minutes = seconds / 60;
        long sec = seconds % 60;

        return String.format("%d:%d:%d", hours, minutes, sec);
    }
}

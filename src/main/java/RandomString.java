public class RandomString {
    private static final String CHARS_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvxyz";

    public static String getRandomAlphabeticString(int targetLength) {
        StringBuilder sb = new StringBuilder(targetLength);

        for (int i = 0; i < targetLength; i++) {
            int index = (int)(CHARS_POOL.length() * Math.random());
            sb.append(CHARS_POOL.charAt(index));
        }

        return sb.toString();
    }
}

public class ColorfulRoad {

    public int getMin(String road) {
        int dp[] = new int[road.length()];
        int from[] = new int[road.length()];

        dp[0] = 0;
        from[0] = 0;
        for (int i = 1; i < dp.length; i++)
            dp[i] = Integer.MAX_VALUE;

        boolean gFound = false;
        boolean bFound = false;
        for (int i = 1; i < dp.length; i++) {
            char currentChar = road.charAt(i);
            if (currentChar == 'B' && gFound) {
                bFound = true;
            }
            if (currentChar == 'G' && bFound == false) {
                dp[i] = i * i;
                from[i] = 0;
                gFound = true;
                continue;
            }
            from[i] = -1;
            for (int j = i - 1; j >= 0; --j) {
                if (from[j] != -1 && currentChar == 'R' && road.charAt(j) == 'B') {
                    if (from[j] != -1 && dp[i] > (dp[j] + (i - j) * (i - j))) {
                        dp[i] = (dp[j] + (i - j) * (i - j));
                        from[i] = j;
                    }
                } else if (from[j] != -1 && currentChar == 'G' && road.charAt(j) == 'R') {
                    if (dp[i] > (dp[j] + (i - j) * (i - j))) {
                        dp[i] = (dp[j] + (i - j) * (i - j));
                        from[i] = j;
                    }
                } else if (from[j] != -1 && currentChar == 'B' && road.charAt(j) == 'G') {
                    if (dp[i] > (dp[j] + (i - j) * (i - j))) {
                        dp[i] = (dp[j] + (i - j) * (i - j));
                        from[i] = j;
                    }
                }
            }
        }

        int i = road.length() - 1;
        String path = "";
        while (from[i] != 0) {
            if (from[i] == -1)
                return -1;
            path = path + road.charAt(from[i]);
            i = from[i];
        }
        return dp[road.length() - 1];
    }
}

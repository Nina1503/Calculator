class RomanDecimalConverter {
    int[] decimals = {100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] romans = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public int toDecimal(String romanNumber) {
        int result = 0;
        for (int i = 0; i < decimals.length; i++) {
            while (romanNumber.indexOf(romans[i]) == 0) {
                result += decimals[i];
                romanNumber = romanNumber.substring(romans[i].length());
            }
        }
        return result;
    }

    public String toRoman(int number) {
        int prevElement = decimals[0];
        String result = "";

        prevElement++;
        while (number != 0) {
            for (int i = 0; i < decimals.length; i++) {

                if (number < prevElement && number >= decimals[i]) {
                    result = result + romans[i];
                    number = number - decimals[i];
                }
                prevElement = decimals[i];
            }
        }
        return result;
    }

    public Boolean isRomanNumber(String romanNumber) {
        for (int i = 4; i < romans.length; i++) {
            if (romanNumber.startsWith(romans[i])) {
                return true;
            }
        }
        return false;
    }

}

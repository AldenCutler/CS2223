import java.util.ArrayList;
import java.util.Collections;

public class BinaryReflectedGrayCode {

    /**
     * Generates recursively the binary reflected gray code of order n
     * @param n a positive integer representing the order of the binary reflected gray code
     * @return a list of all bit strings of size n composing the gray code
     */
    static ArrayList<String> BRGC(int n) {
        // if n = 1 make list L containing bit strings 0 and 1 in this order
        if (n == 1) {
            ArrayList<String> L = new ArrayList<>();
            L.add("0");
            L.add("1");
            return L;
        }

        // else generate list L1 of bit strings of size n-1 using BRGC(n-1)
        ArrayList<String> L1 = BRGC(n - 1);

        // copy list L1 to list L2 in reversed order
        ArrayList<String> L2 = new ArrayList<>(L1);
        Collections.reverse(L2);

        // add 0 in front of each bit string in list L1, add 1 in front of each bit string in list L2
        for (int i = 0; i < L1.size(); i++) {
            L1.set(i, "0" + L1.get(i));
            L2.set(i, "1" + L2.get(i));
        }

        // append L2 to L1 to get list L
        L1.addAll(L2);

        // return list L
        return L1;
    }

    /**
     * Creates a sequence of names representing which variables are changing in each step of the gray code.
     * Ex: The partial sequence up to six for a 5-bit gray code is: 00000, 00001, 00011, 00010, 00110, 00111:
     * a joins, b joins, a fades, c joins, a joins, b fades.
     * @param grayCode the gray code to be converted to names
     * @return a list of names representing which variables are changing in each step of the gray code
     */
    static ArrayList<String> getAction(ArrayList<String> grayCode) {
        ArrayList<String> names = new ArrayList<>();

        // base case, no actions are performed
        names.add("...");

        String[] players = {"Gomer", "Fleek", "Elmer", "Dietz", "Crizz", "Berty", "Alfie"};

        // an action only occurs if grayCode[i[j]] != grayCode[(i-1)[j]]
        //      if grayCode[i[j]] == 1 and grayCode[(i-1)[j]] == 0, then player j joins
        //      if grayCode[i[j]] == 0 and grayCode[(i-1)[j]] == 1, then player j fades
        // if grayCode[i[j]] == 1 and grayCode[i-1[j]] == 1 or grayCode[i[j]] == 0 and grayCode[i-1[j]] == 0,
        //      then don't do anything
        for (int i = 1; i < grayCode.size(); i++) {
            StringBuilder name = new StringBuilder();
            for (int j = 0; j < grayCode.get(i).length(); j++) {
                if (grayCode.get(i).charAt(j) != grayCode.get(i - 1).charAt(j)) {
                    if (grayCode.get(i).charAt(j) == '1') {
                        name.append(players[j]).append(" joins, ");
                    } else {
                        name.append(players[j]).append(" fades, ");
                    }
                }
            }
            // don't add a comma after the last name
            name = new StringBuilder(name.substring(0, name.length() - 2));
            names.add(name.toString());
        }
        return names;
    }

    /**
     * Creates a sequence of names representing which players are playing in each step of the gray code.
     * @param grayCode the gray code to be converted to names
     * @return a list of names representing which players are playing in each step of the gray code
     */
    static ArrayList<String> getPlayers(ArrayList<String> grayCode) {
        ArrayList<String> players = new ArrayList<>();
        String[] names = {"Gomer", "Fleek", "Elmer", "Dietz", "Crizz", "Berty", "Alfie"};

        // base case, no players are playing
        players.add("Silent stage");

        // for each bit string in the gray code, add the names of the players playing to the list
        for (int i = 1; i < grayCode.size(); i++) {
            StringBuilder name = new StringBuilder();
            for (int j = 0; j < grayCode.get(i).length(); j++) {
                if (grayCode.get(i).charAt(j) == '1') {
                    name.append(names[j]).append(" & ");
                }
            }
            // don't add an ampersand after the last name
            name = new StringBuilder(name.substring(0, name.length() - 2));
            players.add(name.toString());
        }

        return players;
    }

    public static void main(String[] args) {
        ArrayList<String> grayCode = BRGC(7);
        ArrayList<String> actions = getAction(grayCode);
        ArrayList<String> players = getPlayers(grayCode);

        // print table header
        System.out.printf("%-6.30s %-10.30s %-60.60s %-30.30s\n",
                "Index", "Gray Code", "Players Playing", "Action");

        // print table rows
        for (int i = 0; i < grayCode.size(); i++) {
            System.out.printf("%-6.30s %-10.30s %-60.60s %-30.30s\n",
                    i, grayCode.get(i), players.get(i), actions.get(i));
        }
    }
}

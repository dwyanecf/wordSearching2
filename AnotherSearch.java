package wordSearching2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnotherSearch {
    public static void main(String[] args) {
        char[][] array ={
                { 's', 't', 'a', 'c' },
                { 'x', 'f', 'l', 'o' },
                { 'x', 'x', 'x', 'v' },
                { 'x', 'x', 'x', 'e' },
                { 'x', 'x', 'x', 'r' }
        };

        List<String>  list= findWords1(array);

        for(String s : list)
            System.out.println(s);
    }

    public static List<String> findWords1(char[][] array) {

        Set<String> resultSet = new HashSet<>();

        Trie trie = new Trie();
        int m=array.length;
        int n=array[0].length;
        StringBuilder sb =new StringBuilder();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                dfsHelper2(array, sb, i, j,resultSet);
            }
        }
        return new ArrayList<>(resultSet);
    }

    public static void dfsHelper2(char[][] array, StringBuilder sb, int i, int j,  Set<String> resultSet){
        int m=array.length,n=array[0].length;
        if(i<0 || j<0||i>=m||j>=n){
            return;
        }

        sb.append(array[i][j]);


        if(sb.length()>=4) {
            resultSet.add(sb.toString());
        }

        dfsHelper2(array, sb, i-1, j, resultSet);
        dfsHelper2(array, sb, i+1, j, resultSet);
        dfsHelper2(array, sb, i, j-1, resultSet);
        dfsHelper2(array, sb, i, j+1, resultSet);
        dfsHelper2(array, sb, i-1, j-1, resultSet);
        dfsHelper2(array, sb, i+1, j+1, resultSet);

    }
}

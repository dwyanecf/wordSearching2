package wordSearching2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchOne {

    public static void main(String[] args) {

        /*
            Here is my algorithms bellow, first of all I don't know how to using online dictionary API to implement this
            method, but I think my logic for this puzzle is correct.

            I manually input the char array and string dictionary which containing words.
            This will return all the matching words in the dictionary we have input.

            First I decided to use common DFS algorithm to finished it. But I decided to use a more efficient data structure
            Trie Tree to implement because If the current candidate does not exist in all words' prefix, we can stop
            backtracking immediately. This can be done by using a trie structure.

            I tried my best, I will continue learning dictionary API online in the future.

         */



        // Input puzzle array

        char[][] array ={
                { 'w', 't', 'a', 'c', 'k' },
                { 'x', 'w', 'l', 'o', 'w' },
                { 'x', 'x', 'w', 'v', 'x' },
                { 'x', 'x', 'x', 'e', 'x' },
                { 'x', 'x', 'x', 'r', 'x' },
        };

        //Dictionary

        String[] words= {"wtack","xexfs","abcz","xewww","xxwok"};

        List<String> list = findWords2(array,words);
        for(String s : list)
        {
            //print each one int the array matching the dictionary words.
            System.out.println(s);
        }

    }


    public static List<String> findWords2(char[][] board, String[] words) {

        //using Hash Set to store matching words
        Set<String> result = new HashSet<String>();
        Trie trie = new Trie();
        for(String word: words){
            trie.insert(word);
        }
        int m=board.length;
        int n=board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                //using DFS to traverse the whole array
                dfsHelper(board, visited, "", i, j, trie,result);
            }
        }
        return new ArrayList<>(result);
    }

    public static void dfsHelper(char[][] array, boolean[][] visited, String str, int i, int j, Trie trie, Set<String> resultSet){
        int m=array.length,n=array[0].length;
        //array out of bound check
        if(i<0 || j<0||i>=m||j>=n){
            return;
        }
        //if visited before stop backtracking
        else if(visited[i][j])
            return;
        str = str + array[i][j];
        //if add current character doesn't fit the rule stop
        if(!trie.startsWith(str))
            return;
        else if(trie.search(str)){
            //check if the string length > 4
            if(str.length()>=4)
                resultSet.add(str);
        }
        visited[i][j]=true;
        //8 directions of traverse
        dfsHelper(array, visited, str, i-1, j, trie, resultSet);
        dfsHelper(array, visited, str, i+1, j, trie, resultSet);
        dfsHelper(array, visited, str, i, j-1, trie, resultSet);
        dfsHelper(array, visited, str, i, j+1, trie, resultSet);
        dfsHelper(array, visited, str, i-1, j-1, trie, resultSet);
        dfsHelper(array, visited, str, i+1, j+1, trie, resultSet);
        dfsHelper(array, visited, str, i-1, j+1, trie, resultSet);
        dfsHelper(array, visited, str, i+1, j-1, trie, resultSet);
        visited[i][j]=false;
    }
}

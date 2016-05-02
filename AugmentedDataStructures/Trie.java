/**
 * <H1>Trie.java</H1>
 * <p>
 * This file contains implementation of a Trie
 * or a PrefixTree there is a TrieNode.java file
 * in the repository.Please make sure yo compile the program
 * along with TrieNode.java as it is it's dependency.
 * </p>
 *
 * @author Ayush Anand<iamayushanand@gmail.com>
 *
 * Copyright (C) 2016 Ayush Anand
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * */
public class Trie{

    public static TrieNode rootNode = new TrieNode('.');

    public static void insert(String word){
        TrieNode curNode = rootNode;
        TrieNode n;
        for(int i=0;i<word.length();i++){
            if(curNode.children[word.charAt(i)-97]==null){
                n=new TrieNode(word.charAt(i));
                curNode.children[word.charAt(i)-97] = n;
                curNode = n;
            }else{
                curNode=curNode.children[word.charAt(i)-97];
            }
        }

        curNode.isLeaf = true;
        curNode.Word = word;
    }

    public static void InsertOneWord(String word){
        if(word.length()>=1){
        insert(word);
        InsertOneWord(word.substring(1));
        }
    }

    public static void InsertMultWords(String[] word){
        for(int i=0;i<word.length;i++){

            insert(word[i]);


        }
    }

    public static boolean Search(String word){
        TrieNode curNode = rootNode;
        for(int i =0;i<word.length();i++){
            if(curNode.children[word.charAt(i)-'a']==null){
                return false;
            }else{
                curNode = curNode.children[word.charAt(i)-'a'];
            }
        }

        if(curNode.isLeaf){return true;}
        return false;
    }

    public static void printText(TrieNode n, boolean isThisLeaf){
        String printThis = "";

        if(isThisLeaf){
            if(n.isLeaf){
                System.out.println(n.Word);
            }
        }

        for(int i=0;i<n.children.length;i++){
            if(n.children[i]!=null ){

                if(n.children[i].isLeaf){
                    System.out.println(n.children[i].Word);

                }
                printText(n.children[i],false);
            }

        }
    }
    public static void Suggestion(String word){
        String[] suggestions = new String[21];
        TrieNode curNode = rootNode;
        for(int i =0;i<word.length();i++){
            if(curNode.children[word.charAt(i)-'a']==null){
                printText(curNode,true);
            }else{
                curNode = curNode.children[word.charAt(i)-'a'];

            }
        }

    }

    public static void PrefixSuggestion(String word){
        String[] suggestions = new String[21];
        TrieNode curNode = rootNode;
        for(int i =0;i<word.length();i++){

                curNode = curNode.children[word.charAt(i)-'a'];


        }
        printText(curNode,true);

    }


    public static void printTrie(TrieNode root){
        System.out.println(root.value);
        System.out.println("|");
        System.out.println("---------------------------------------------------------");
        System.out.println();
        for (int i=0;i<root.children.length;i++){if(root.children[i]!=null)System.out.print(root.children[i].value);}
        System.out.println();
        System.out.println("---------------------------------------------------------");
        System.out.println("|");
        for(int i=0;i<root.children.length;i++){
        if(root.children[i]!=null){
        printTrie(root.children[i]);
        }
        }
    }

    public static void main(String[] args)
    {
        InsertMultWords(new String[]{"thou","shall", "not", "live", "should"});


        printTrie(rootNode);
        System.out.println(Search("to"));
        System.out.println(Search("te"));
        //printText(rootNode.children[19],true);
        PrefixSuggestion("sh");

    }
}


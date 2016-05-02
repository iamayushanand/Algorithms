public class TrieNode
{
    char value;
    public TrieNode[] children = new TrieNode[26];
    boolean isLeaf;
    String Word="";

    public TrieNode(char c){
        this.value=c;
    }

    public TrieNode(char c,String Word){
        this.value=c;
    }


}

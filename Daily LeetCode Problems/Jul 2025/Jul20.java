/* 1948. Delete Duplicate Folders in System
Due to a bug, there are many duplicate folders in a file system.
You are given a 2D array paths, where paths[i] is an array representing an absolute path to the ith folder in the file system.
For example, ["one", "two", "three"] represents the path "/one/two/three".
Two folders (not necessarily on the same level) are identical if they contain the same non-empty set of identical subfolders and underlying subfolder structure.
The folders do not need to be at the root level to be identical. If two or more folders are identical, then mark the folders as well as all their subfolders.
For example, folders "/a" and "/b" in the file structure below are identical. They (as well as their subfolders) should all be marked:
/a
/a/x
/a/x/y
/a/z
/b
/b/x
/b/x/y
/b/z
However, if the file structure also included the path "/b/w", then the folders "/a" and "/b" would not be identical.
Note that "/a/x" and "/b/x" would still be considered identical even with the added folder.
Once all the identical folders and their subfolders have been marked, the file system will delete all of them.
The file system only runs the deletion once, so any folders that become identical after the initial deletion are not deleted.
Return the 2D array ans containing the paths of the remaining folders after deleting all the marked folders.
The paths may be returned in any order.

Example 1:
Input: paths = [["a"],["c"],["d"],["a","b"],["c","b"],["d","a"]]
Output: [["d"],["d","a"]]
Explanation: The file structure is as shown.
Folders "/a" and "/c" (and their subfolders) are marked for deletion because they both contain an empty
folder named "b".

Example 2:
Input: paths = [["a"],["c"],["a","b"],["c","b"],["a","b","x"],["a","b","x","y"],["w"],["w","y"]]
Output: [["c"],["c","b"],["a"],["a","b"]]
Explanation: The file structure is as shown. 
Folders "/a/b/x" and "/w" (and their subfolders) are marked for deletion because they both contain an empty folder named "y".
Note that folders "/a" and "/c" are identical after the deletion, but they are not deleted because they were not marked beforehand.

Example 3:
Input: paths = [["a","b"],["c","d"],["c"],["a"]]
Output: [["c"],["c","d"],["a"],["a","b"]]
Explanation: All folders are unique in the file system.
Note that the returned array can be in a different order as the order does not matter.

Constraints:
1 <= paths.length <= 2 * 10^4
1 <= paths[i].length <= 500
1 <= paths[i][j].length <= 10
1 <= sum(paths[i][j].length) <= 2 * 10^5
path[i][j] consists of lowercase English letters.
No two paths lead to the same folder.
For any folder not at the root level, its parent folder will also be in the input. */


import java.util.AbstractMap;

class Solution {
    class Node{
        String val;
        String subFolder;
        Map<String, Node> children;

        Node(String val){
            this.val = val;
            this.subFolder = "";
            this.children = new HashMap<>();
        }
    }

    private Node getNode(String val){
        return new Node(val);
    }

    private void insert(Node root, List<String> path){
        for(String folder : path){
            root.children.putIfAbsent(folder, getNode(folder));
            root = root.children.get(folder);
        }
    }

    private String populateNodes(Node root, Map<String, Integer> subFolderMap){
        List<Map.Entry<String, String>> subFolderPaths = new ArrayList<>();

        for(Map.Entry<String, Node> entry : root.children.entrySet()){
            String subFolderResult = populateNodes(entry.getValue(), subFolderMap);
            subFolderPaths.add(new AbstractMap.SimpleEntry<>(entry.getKey(), subFolderResult));
        }

        subFolderPaths.sort(Comparator.comparing(Map.Entry::getKey));

        StringBuilder completePath = new StringBuilder();
        for(Map.Entry<String, String> entry : subFolderPaths){
            completePath.append("(").append(entry.getKey()).append(entry.getValue()).append(")");
        }

        root.subFolder = completePath.toString();

        if(!completePath.toString().isEmpty()){
            subFolderMap.put(completePath.toString(), subFolderMap.getOrDefault(completePath.toString(), 0) + 1);
        }

        return completePath.toString();
    }

    private void removeDuplicates(Node root, Map<String, Integer> subFolderMap){
        Iterator<Map.Entry<String, Node>> it = root.children.entrySet().iterator();

        while(it.hasNext()){
            Map.Entry<String, Node> entry = it.next();
            Node child = entry.getValue();

            if(!child.subFolder.isEmpty() && subFolderMap.get(child.subFolder) > 1){
                it.remove();
            }else{
                removeDuplicates(child, subFolderMap);
            }
        }
    }

    private void constructResult(Node root, List<String> path, List<List<String>> result){
        for(Map.Entry<String, Node> entry : root.children.entrySet()){
            path.add(entry.getKey());
            result.add(new ArrayList<>(path));
            constructResult(entry.getValue(), path, result);
            path.remove(path.size() - 1);
        }
    }

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Node root = getNode("/");

        for(List<String> path : paths){
            insert(root, path);
        }

        Map<String, Integer> subFolderMap = new HashMap<>();
        populateNodes(root, subFolderMap);

        removeDuplicates(root, subFolderMap);

        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        constructResult(root, path, result);

        return result;
    }
}

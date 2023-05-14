package com.interview.interview.collection.tree;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class MyTrieTree<V> {
    private static final int R = 26;
    private TrieNode<V> root = null;

    @Getter
    private int size = 0;

    /***** 增/改 *****/

    // 在 Map 中添加 key
    public void put(String key, V val) {
        if (!containsKey(key)) {
            // 新增键值对
            size++;
        }
        // 需要一个额外的辅助函数，并接收其返回值
        root = put(root, key, val, 0);
    }

    private TrieNode<V> put(TrieNode<V> node, String key, V value, int idx) {
        if (node == null) {
            node = new TrieNode<>();
        }
        if (idx == key.length()) {
            node.val = value;
            return node;
        }
        char c = key.charAt(idx);

        node.children[c - 97] = put(node.children[c - 97], key, value, idx + 1);

        return node;
    }

    /***** 删 *****/

    // 删除键 key 以及对应的值
    public void remove(String key) {
        if (!containsKey(key)) {
            return;
        }
        // 递归修改数据结构要接收函数的返回值
        root = remove(root, key, 0);
        size--;
    }

    private TrieNode<V> remove(TrieNode<V> node, String key, int idx) {
        if (node == null) return null;

        if (idx == key.length()) {
            // 找到了 key 对应的 TrieNode，删除 val
            node.val = null;
        } else {
            char c = key.charAt(idx);
            // 递归去子树进行删除
            node.children[c] = remove(node.children[c], key, idx + 1);
        }

        // 后序位置，递归路径上的节点可能需要被清理
        if (node.val != null) {
            // 如果该 TireNode 存储着 val，不需要被清理
            return node;
        }

        // 检查该 TrieNode 是否还有后缀
        for (int c = 0; c < R; c++) {
            if (node.children[c] != null) {
                // 只要存在一个子节点（后缀树枝），就不需要被清理
                return node;
            }
        }

        return null;
    }

    /***** 查 *****/

    // 搜索 key 对应的值，不存在则返回 null
    // get("the") -> 4
    // get("tha") -> null
    public V get(String key) {
        TrieNode<V> node = root.getNode(key);
        if (node == null || node.val == null) return null;
        return node.val;
    }

    // 判断 key 是否存在在 Map 中
    // containsKey("tea") -> false
    // containsKey("team") -> true
    public boolean containsKey(String key) {
        return get(key) != null;
    }

    // 在 Map 的所有键中搜索 query 的最短前缀
    // shortestPrefixOf("themxyz") -> "the"
    public String shortestPrefixOf(String query) {
        TrieNode<V> p = root;
        // 从节点 node 开始搜索 key
        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                // 无法向下搜索
                return "";
            }
            if (p.val != null) {
                // 找到一个键是 query 的前缀
                return query.substring(0, i);
            }
            // 向下搜索
            char c = query.charAt(i);
            p = p.children[c];
        }

        if (p != null && p.val != null) {
            // 如果 query 本身就是一个键
            return query;
        }
        return "";
    }

    // 在 Map 的所有键中搜索 query 的最长前缀
    // longestPrefixOf("themxyz") -> "them"
    public String longestPrefixOf(String query) {
        TrieNode<V> p = root;
        int max_len = 0;

        // 从节点 node 开始搜索 key
        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                // 无法向下搜索
                break;
            }
            if (p.val != null) {
                max_len = i;
            }
            // 向下搜索
            char c = query.charAt(i);
            p = p.children[c];
        }
        if (p != null && p.val != null) {
            // 如果 query 本身就是一个键
            return query;
        }
        return query.substring(0, max_len);
    }

    // 搜索所有前缀为 prefix 的键
    // keysWithPrefix("th") -> ["that", "the", "them"]
    public List<String> keysWithPrefix(String prefix) {
        List<String> res = new LinkedList<>();
        // 找到匹配 prefix 在 Trie 树中的那个节点
        TrieNode<V> x = root.getNode(prefix);
        if (x == null) {
            return res;
        }

        // DFS 遍历以 x 为根的这棵 Trie 树
        traverse(x, new StringBuilder(prefix), res);
        return res;
    }

    private void traverse(TrieNode<V> node, StringBuilder path, List<String> res) {
        // 到达 Trie 树底部叶子结点
        if (node == null) return;

        // 找到一个 key，添加到结果列表中
        if (node.val != null) res.add(path.toString());

        for (char c = 0; c < R; c += 1) {
            path.append(c);
            traverse(node.children[c], path, res);
            // 撤销选择
            path.deleteCharAt(path.length() - 1);
        }
    }

    // 判断是和否存在前缀为 prefix 的键
    // hasKeyWithPrefix("tha") -> true
    // hasKeyWithPrefix("apple") -> false
    public boolean hasKeyWithPrefix(String prefix) {
        return !keysWithPrefix(prefix).isEmpty();
    }

    // 通配符 . 匹配任意字符，搜索所有匹配的键
    // keysWithPattern("t.a.") -> ["team", "that"]
    public List<String> keysWithPattern(String pattern) {
        List<String> res = new LinkedList<>();
        traverse(root, new StringBuilder(), pattern, 0, res);
        return res;
    }

    private void traverse(TrieNode<V> node, StringBuilder path, String pattern, int i, List<String> res) {
        // 树枝不存在，即字符 pattern[i-1] 匹配失败
        if (node == null) return;

        if (i == pattern.length()) {
            // pattern 匹配完成
            if (node.val != null) {
                // 如果这个节点存储着 val，则找到一个匹配的键
                res.add(path.toString());
            }
            return;
        }

        char c = pattern.charAt(i);
        if (c == '.') {
            for (char j = 0; j < R; j += 1) {
                path.append(j);
                traverse(node.children[j], path, pattern, i + 1, res);
                // 撤销选择
                path.deleteCharAt(path.length() - 1);
            }
        } else {
            path.append(c);
            traverse(node.children[c - 97], path, pattern, i + 1, res);
            // 撤销选择
            path.deleteCharAt(path.length() - 1);
        }
    }

    // 通配符 . 匹配任意字符，判断是否存在匹配的键
    // hasKeyWithPattern(".ip") -> true
    // hasKeyWithPattern(".i") -> false
    public boolean hasKeyWithPattern(String pattern) {
        return !keysWithPattern(pattern).isEmpty();
    }


    private static class TrieNode<V> {
        V val = null;
        TrieNode<V>[] children = new TrieNode[R];

        private TrieNode<V> getNode(String key) {
            TrieNode<V> p = this;
            // 从节点 node 开始搜索 key
            for (int i = 0; i < key.length(); i++) {
                if (p == null) {
                    // 无法向下搜索
                    return null;
                }
                // 向下搜索
                char c = key.charAt(i);
                p = p.children[c];
            }
            return p;
        }
    }
}

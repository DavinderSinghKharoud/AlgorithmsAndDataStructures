package Preparation;

import java.util.*;

/**
 * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
 *
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
 *
 * The canonical path should have the following format:
 *
 * The path starts with a single slash '/'.
 * Any two directories are separated by a single slash '/'.
 * The path does not end with a trailing '/'.
 * The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
 * Return the simplified canonical path.
 *
 *
 *
 * Example 1:
 *
 * Input: path = "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 * Example 2:
 *
 * Input: path = "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 * Example 3:
 *
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 * Example 4:
 *
 * Input: path = "/a/./b/../../c/"
 * Output: "/c"
 *
 */

public class SimplifyPath {

    public String simplifyPath(String path) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        String[] split = path.split("/");
        int len = split.length;
        for (String s : split) {
            if (s.isEmpty() || s.equals(".") || s.equals("/")) continue;
            if (s.equals("..")) {
                if (!stack.isEmpty()) stack.pollLast();
            } else {
                // "////" or directory
                stack.addLast(s);
            }
        }

        StringBuilder sbr = new StringBuilder();
        sbr.append("/");
        for (String s : stack) {
            sbr.append(s).append("/");
        }
        if (sbr.length() > 1) sbr.deleteCharAt(sbr.length() - 1);
        return sbr.toString();
    }
}

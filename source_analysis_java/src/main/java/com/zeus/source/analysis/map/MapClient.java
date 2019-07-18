package com.zeus.source.analysis.map;


import java.util.ArrayList;

/**
 * =======================================
 * Created by kevint on 2019-06-04.
 * =======================================
 */
public class MapClient {
    public static void main(String[] args) {
//        testLinkListReverse();

//        testTreeBFS();

//        testQuickSort();

//        testRemoveDuplicated();

        testLinkCycle();
    }


    //单链表反转的递归方法和非递归方法

    static void testLinkListReverse() {

        System.out.println("=============单链表反转===========");
        //创建链表
        Node<Integer> head = null;
        Node<Integer> tmp = null;
        for (int i = 0; i < 10; i++) {
            Node<Integer> node = new Node<>(i, null);
            if (head == null) {
                head = node;
                tmp = head;
            } else {
                tmp.next = node;
                tmp = node;
            }
        }
        tmp = head;
        while (tmp != null) {
            System.out.print(tmp.value + " ");
            tmp = tmp.next;
        }
        System.out.println("===反转后=");

        tmp = reverseLink2(head);
        while (tmp != null) {
            System.out.print(tmp.value + " ");
            tmp = tmp.next;
        }
    }

    //反转非递归算法
    static <T> Node<T> reverseLink(Node<T> head) {

        Node<T> cur = head;
        Node<T> pre = null;
        Node<T> next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    //反转递归算法
    static <T> Node<T> reverseLink2(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node<T> newHead = reverseLink2(head.next);

        head.next.next = head;
        head.next = null;
        return newHead;
    }

    static class Node<T> {

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        T value;
        Node<T> next;
    }

    //二叉树的层序遍历（BFS）
    static void testTreeBFS() {
        System.out.println("=============二叉树的层序遍历（BFS）===========");
        Tree<Integer> tmp = new Tree<>(0);
        tmp.left = new Tree<>(1);
        tmp.right = new Tree<>(2);

        tmp.left.left = new Tree<>(3);
        tmp.left.right = new Tree<>(4);

        tmp.right.left = new Tree<>(5);
        tmp.right.right = new Tree<>(6);

        ArrayList<Integer> save = new ArrayList<>();
        bfs(tmp, save);
        System.out.println("\n========xx");
        for (Integer v : save) {

            System.out.print(v + " ");
        }

    }

    static <T> void bfs(Tree<T> head, ArrayList<T> save) {


        System.out.print(head.v + " ");
        save.add(head.v);
        if (head.left != null) {
            bfs(head.left, save);
        }

        if (head.right != null) {
            bfs(head.right, save);
        }

    }

    static class Tree<T> {
        T v;
        Tree<T> left;
        Tree<T> right;

        public Tree(T v) {
            this.v = v;
        }
    }

    static void testQuickSort() {
        int arr[] = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        quickSort(arr, 0, arr.length - 1);

        System.out.println("=============testQuickSort===========");
        for (int v : arr) {
            System.out.print(v + " ");
        }
    }

    static void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }

        int i = left;
        int j = right;

        int key = arr[i];

        while (i != j) {
            while (arr[j] >= key && i < j) {
                j--;
            }
            while (arr[i] <= key && i < j) {
                i++;
            }

            if (i < j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }

        arr[left] = arr[i];
        arr[i] = key;

        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);


    }

    //字符串去重 abcab --> abc
    static void testRemoveDuplicated() {
        System.out.println("=============testRemoveDuplicated=============");
        char[] s = "asfdasdf".toCharArray();

        char[] flags = new char[256];

        int index = 0;
        for (int i = 0; i < s.length; i++) {
            char c = s[i];
            if (flags[c] != 1) {
                s[index++] = c;
                flags[c] = 1;
            }
        }

        for (int i = 0; i < index; i++) {
            System.out.print(s[i] + " ");
        }
    }


    static void testLinkCycle() {
        System.out.println("=============单链是否有环===========");
        //创建链表
        Node<Integer> head = null;
        Node<Integer> tmp = null;

        Node<Integer> cycl = null;
        for (int i = 0; i < 10; i++) {
            Node<Integer> node = new Node<>(i, null);
            if (head == null) {
                head = node;
                tmp = head;
            } else {
                tmp.next = node;
                tmp = node;

                if (i == 5) {
                    cycl = node;
                }
            }

        }

        tmp.next = cycl;


        checkLinkCycle(head);
    }

    static <T> void checkLinkCycle(Node<T> head) {
        Node<T> walker = head;
        Node<T> runner = head.next;

        while (runner != null && runner.next != null && runner != walker) {
            walker = walker.next;
            runner = runner.next.next;
        }

        if (runner == null) {
            System.out.println("no cycl");
        } else {
            System.out.println("has cycl");
        }


    }
}

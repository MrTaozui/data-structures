package com01.avl;

/**
 * @author taojj .
 * <p>
 * 平衡二叉树
 * 问题的产生，在添加二叉树的时候，会使一边过长
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i <arr.length ; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("进行中序遍历");
        avlTree.infixOrder();
        System.out.println("查看父节点");
        Node parent = avlTree.searchaParent(7);
        System.out.println(parent);
    }
}


class AVLTree {
    private Node root;

    public void add(Node node) {
        if (this.root == null) {
            this.root = node;
        } else {
            this.root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root == null) {
            System.out.println("树为空");
            return;
        }
        this.root.infixOrder();
    }

    /**
     * 查找目标节点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        Node result = null;
        if (root != null) {
            result = this.root.search(value);
        }
        return result;
    }

    /**
     * 寻找父节点
     *
     * @param value
     * @return
     */
    public Node searchaParent(int value) {
        if (root == null || root.getValue() == value) {
            return null;
        }
        return root.searchaParent(value, null);

    }

    /**
     * 删除节点
     *
     * @param value
     * @return
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //找到需要删除的节点
            Node targetNode = search(value);
            if (targetNode == null) {//如果没有找到终止操作
                return;
            }
            //如果发现删除的是根节点
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
                return;
            }
            //寻找父节点
            Node parent = searchaParent(value);
            //如果删除的是叶子节点
            if (targetNode.getLeft() == null && targetNode.getRight() == null) {
                //要删除的是左子树
                if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                    parent.setLeft(null);
                }//要删除的是右叶子
                else if (parent.getRight() != null && parent.getRight().getValue() == value) {
                    parent.setRight(null);
                }
            }
            //删除有左右两棵子树的节点
            else if (targetNode.getLeft() != null && targetNode.getRight() != null) {
                int rightMin = delRightTreeMin(targetNode.getRight());
                targetNode.setValue(rightMin);
            } else {//删除只有一棵叶子节点的数
                if (parent.getLeft() != null && parent.getLeft().getValue() == value) {//要删除的是父节点的左子树
                    parent.setLeft(targetNode.getLeft());
                } else if (parent.getRight() != null && parent.getRight().getValue() == value) {//要删除的是父节点的右子树
                    parent.setRight(targetNode.getRight());
                }

            }


        }

    }

    //把右边最小的删除并返回  或者 把 左边最大的拿上来
    public int delRightTreeMin(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        delNode(node.getValue());//删除这个叶子节点
        return node.getValue();

    }

    //把左边最大的删除并返回
    public int delLeftTreeMin(Node node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }
        delNode(node.getValue());//删除此叶子节点
        return node.getValue();

    }


    public Node getRoot() {
        return root;
    }

}


class Node {
    private int value;//值

    private Node left;//左子节点

    private Node right;//右子节点


    public Node(int value) {
        this.value = value;

    }

    /**
     * 返回当前树的高度
     *
     * @return
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 返回左子树的高度
     *
     * @return
     */
    public int leftHeight() {
        if (this.left == null) {
            return 0;
        }
        return this.left.height();

    }

    /**
     * 返回右子树的高度
     *
     * @return
     */
    public int rightHeight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.height();
    }

    /**
     * 左旋方法 右边的子树比左边高，需要往左旋转
     * 把左边的树的高度升高
     * <p>
     * 可以画图演示步骤 往哪里旋转新节点就画在那一侧 有助于理解
     */
    private void leftRotate() {
        //为了不破坏以前节点的关系，借用新建立的节点来旋转,当前节点不要移动，因为上层的节点
        //还跟此节点有联系
        //只是移动子树和值
        Node newNode = new Node(value);
        newNode.left = this.left;
        newNode.right = this.right.left;
        value = right.value;//移动值
        right = right.right;//移动子树
        left = newNode;
    }

    //右旋方法
    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;

    }

    /**
     * 为当前节点（树）添加新节点
     *
     * @param node
     */
    public void add(Node node) {
        if (node.getValue() < this.value) {//如果待添加节点的值小与当前节点 往左子树添加
            if (this.left == null) {
                left = node;
            } else {
                left.add(node);//往左子树添加
            }
        } else {
            if (this.right == null) {
                right = node;
            } else {
                right.add(node);
            }
        }
        //添加完一个节点后如果右子树的高度-左子树的高度>1 则需要进行左旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                //先对右子树进行左旋转 降低其高度
                right.rightRotate();
                this.leftRotate();
            } else {
                this.leftRotate();
            }
            return; //旋转后返回， 防止下面再旋转
        }
        if (leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树高度大于它的左子树的高度
                if(left!=null&&left.rightHeight()>left.leftHeight()){
                    //对左子树右旋转，降低其高度
                    left.leftRotate();
                    this.rightRotate();
                }else {
                    rightRotate();
                }
        }


    }


    //当前树的中序遍历

    /**
     * 根据二叉排序树的性质 中序遍历就是排序的 因为就拿三个节点的树来说  比当前树根节点小的
     * 都在左边，比根节点大的 都在右边  而中序遍历的顺序为左 当前 右  -> 小 中 大 这种性质从叶子节点
     * 延申到整个树，所以这种遍历方式就是有序的
     */
    public void infixOrder() {

        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }


    }

    /**
     * 查找节点
     * <p>
     * 因为按照二叉排序树的特性 所以有
     * 下面的查找规律 一般来说 比其他普通前中后遍历方式快
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (this.left != null && value < this.value) {
            return this.left.search(value);
        } else if (this.right != null && value > this.value) {
            return this.right.search(value);
        } else {
            return null;
        }

    }

    /**
     * 寻找父节点  为删除节点使用
     * 因为删除节点要找到此节点的父节点
     * <p>
     * 思路：要找到目标节点的父节点，因为是如果当前节点不是，当前节点左右查找，
     * 这个时候当前节点就是父节点，如果左右找到 就返回当前节点。如果不是左右节点变成
     * 当前节点继续查找 如此递归，就方法而言，每次查找，把父节点传过来
     * <p>
     * 思考：此种方式
     *
     * @param value
     * @param parent
     * @return
     */
    public Node searchaParent(int value, Node parent) {
        if (this.value == value) {
            return parent;
        } else if (this.left != null && value < this.value) {
            return this.left.searchaParent(value, this);
        } else if (this.right != null && value > this.value) {
            return this.right.searchaParent(value, this);
        } else {
            return null;
        }

    }

    /**
     * 查找父节点方法二
     *
     * @param value
     * @return
     */
    public Node searchaParent(int value) {
        if (this.right != null && this.right.getValue() == value ||
                this.left != null && this.left.getValue() == value) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchaParent(value);
            } else if (this.right != null) {
                return this.right.searchaParent(value);
            }
        }
        return null;

    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
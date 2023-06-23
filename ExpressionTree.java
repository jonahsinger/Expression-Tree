/*
    Jonah Singer
    Class for ExpressionTree
*/

import java.util.*;

public class ExpressionTree implements ExpressionTreeInterface{

    ExpressionNode root;

    // pop empty stack things left on stack error
    public ExpressionTree (String s){
        // splits string into operands and operators
        // puts in an array
        String [] nodes = s.split(" ");

        // creates a stack to help build the tree
        Stack <ExpressionNode> myStack = new Stack<>();

        // this is in a try catch to check for illegal inputs and illegal postfix expressions
        try {
            // loops through each element in the array
            for (String node : nodes) {
                switch (node) {
                    // for all operators the stack is popped twice
                    // the operator is applied and pushed back on the stack
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                        ExpressionNode rc = myStack.pop();
                        ExpressionNode lc = myStack.pop();
                        myStack.push(new ExpressionNode(node, lc, rc));
                        break;
                    // if the element is not an operator it is checked to see if it is an int
                    // and will catch an error if present. Then if it is an int an expression node
                    // will be created and pushed on the stack.
                    default:
                        Integer.parseInt(node);
                        ExpressionNode temp = new ExpressionNode(node);
                        myStack.push(temp);
                        break;
                }
            }
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Not an accepted operator or operand");
        } catch (EmptyStackException e){
            throw new IllegalArgumentException("Empty stack popped");
        }

        // pops stack once at the end
        this.root = myStack.pop();

        // throws exception if items are still on stack
        if (!myStack.isEmpty()){
            throw new IllegalArgumentException("Items remain on stack");
        }
    }

    // This is a modified version of the text book code for a BST node
    public static class ExpressionNode{
        ExpressionNode(String theElement )
        {
            this( theElement, null, null );
        }

        ExpressionNode( String theElement, ExpressionNode lt, ExpressionNode rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        String element;          // The data in the node
        ExpressionNode left;   // Left child
        ExpressionNode right;  // Right child
    }

    // public eval method calls private recursive eval method
    public int eval(){
        return eval(root);
    }

    //private recursive eval method
    private int eval(ExpressionNode node){
        // if the tree is empty return zero
        if (node == null){
            return 0;
        }
        // if tree has no children return node.element
        if (node.left == null){
            return Integer.parseInt(node.element);
        } else {
            switch (node.element) {
                case "+":
                    return eval(node.left) + eval(node.right);
                case "-":
                    return eval(node.left) - eval(node.right);
                case "*":
                    return eval(node.left) * eval(node.right);
                case "/":
                    return eval(node.left) / eval(node.right);
            }
        }
        // make java happy
        return 0;
    }

    // public postfix method
    public String postfix(){
        return postfix(root);
    }

    // private recursive postfix method
    private String postfix(ExpressionNode node){
        if (node == null){
            return "";
        }
        if (node.left == null){
            return node.element;
        } else {
            return postfix(node.left) + ' ' + postfix(node.right) + ' ' + node.element;
        }
    }

    // public prefix method
    public String prefix(){
        return prefix(root);
    }

    // private recursive prefix method
    private String prefix(ExpressionNode node){
        if (node == null){
            return "";
        }
        if (node.left == null){
            return node.element;
        } else {
            return node.element + ' ' + prefix(node.left) + ' ' + prefix(node.right);
        }
    }

    // public infix method
    public String infix(){
        return infix(root);
    }

    // private recursive infix method
    private String infix(ExpressionNode node){
        if (node == null){
            return "";
        }
        if (node.left == null){
            return node.element;
        } else {
            return '(' + infix(node.left) + ' ' + node.element + ' ' + infix(node.right) + ')';
        }
    }

}

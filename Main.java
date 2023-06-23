public class Main{
	public static void main(String[] args){
		ExpressionTree tree = new ExpressionTree("10 2 - 6 *");

		System.out.println("eval: " + tree.eval());

		System.out.println("prefix: "+ tree.prefix());

		System.out.println("postfix: "+ tree.postfix());

		System.out.println("infix: "+ tree.infix());

		
	}
}
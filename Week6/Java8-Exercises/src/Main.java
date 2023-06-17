import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Product.addDummyData();

        List<Product> bookProducts = products.stream()
                .filter(product -> product.getProductCategory().equals("Books"))
                .collect(Collectors.toList());

        bookProducts.forEach(
                System.out::println
        );
        System.out.println("*************************");

        //Exercise 2
        List<Product> bookProductsByPrice = products.stream()
                .filter(product -> product.getProductCategory().equals("Books")&& product.getProductPrice()>100)
                .collect(Collectors.toList());

        bookProductsByPrice.forEach(
                System.out::println
        );
        System.out.println("*****************");

        //Exercise 3
        List<Product> toysWithDiscount = products.stream()
                .filter(product -> product.getProductCategory().equals("Toys"))
                .map(product -> {
                   return product.withPrice(product.getProductPrice()*0.9);
                })
                .collect(Collectors.toList());
        System.out.println("Toys with discount are: ");
        toysWithDiscount.forEach(System.out::println);

        System.out.println("*******************");

        //Exercise 4
        Optional<Product> minProduct = products.stream()
                .filter(product -> product.getProductCategory().equals("Books"))
                .min(Comparator.comparingDouble(Product::getProductPrice));

        if (minProduct.isPresent()){
            System.out.println("Cheapest  Product in Books Category is " + minProduct.get());
        }else {
            System.out.println("No cheapest Product");
        }

    }
}
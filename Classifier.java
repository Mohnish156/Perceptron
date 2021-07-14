import java.io.File;
import java.lang.reflect.Array;
import java.util.*;

public class Classifier {
    Random random = new Random();
    private Perceptron perceptron;
    private List<Pixel> images;


    public Classifier(){}

    public void CreatePerceptron(){


        int count = 0;
        while(!perceptron.CheckClass(images)){
            count++;
            Pixel image = images.get(random.nextInt(100));
            if(perceptron.ErrorValue(image)==image.PixelClass){}
             else if(image.PixelClass =='O'){

                for(Feature feature : perceptron.getFeatureVals().keySet()){
                    double weight = perceptron.getFeatureVals().get(feature) - feature.ValFeature(image);
                    perceptron.getFeatureVals().put(feature, weight);
                }
            } else if(image.PixelClass == 'X') {

                for(Feature feature : perceptron.getFeatureVals().keySet()){
                    double weight = perceptron.getFeatureVals().get(feature) + feature.ValFeature(image);
                    perceptron.getFeatureVals().put(feature, weight);
                }
            }
        }


        for(Double weights : perceptron.getFeatureVals().values()) {
            System.out.println("Final Weights: " + weights);
        }

        System.out.println("Iterations to convergence: "+ count);



    }

    public static void main(String[] args){
            Classifier classifier = new Classifier();

                File Train = new File(args[0]); ///TRAINING FILE

                classifier.images = Parser.parse(Train);

                Map<Feature, Double> features = new HashMap<>();
                Feature firstFeature = new Feature();
                firstFeature.setFirstFeature(true);
                features.put(firstFeature, (double) 0);

                for (int j = 0; j < 50; j++) {
                    Feature feature = new Feature();
                    features.put(feature, (double) 0);
                }
                classifier.perceptron = new Perceptron(features);
                classifier.CreatePerceptron();

                File test = new File(args[0]); ///TEST FILE

                Pixel image = Parser.parse(test).get(0);

                System.out.println("Predicted class:  " + classifier.perceptron.ErrorValue(image) + "  actual class:  " + image.PixelClass);

            }

}

class Feature {

    private boolean FirstFeature;
    private int[] row;
    private int[] col;
    private String[] sign;


    public Feature(){
        Random random = new Random();

        sign = new String[]{Boolean.toString(random.nextBoolean()), Boolean.toString(random.nextBoolean()), Boolean.toString(random.nextBoolean())};

        col = new int[]{random.nextInt(9), random.nextInt(9), random.nextInt(9)};
        row = new int[]{random.nextInt(9), random.nextInt(9), random.nextInt(9)};
    }

    public double ValFeature(Pixel image){
        if(FirstFeature) {
            return 1;
        }
        double sum=0;
        for(int i=0; i < 3; i++) {
            if (image.values[this.row[i]][this.col[i]].equals(this.sign[i]))sum++;
        }
        return (sum>=2)?1:0;
    }

    public void setFirstFeature(boolean firstFeature) {
        FirstFeature = firstFeature;
    }

}

 class Pixel {

    String[][] values;
    char PixelClass;

    public Pixel(String[][] values, char PixelClass){
        this.values = values;
        this.PixelClass = PixelClass;
    }


}


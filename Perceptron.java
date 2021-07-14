import java.util.List;
import java.util.Map;

public class Perceptron {

    private Map<Feature, Double> FeatureVals;

    public Perceptron(Map<Feature, Double> featureVector){ this.FeatureVals = featureVector; }

    public char ErrorValue(Pixel image){
        double total = 0;
        for(Feature feature : FeatureVals.keySet()){
            double error = FeatureVals.get(feature) * feature.ValFeature(image) ;
            total += error;
        }
        if(total<0) return 'O';
        else return 'X';

    }

    public boolean CheckClass(List<Pixel> images) {
        for(Pixel image : images){
            if(!(ErrorValue(image)==image.PixelClass))
                return false;
        }
        return true;
    }

    public Map<Feature, Double> getFeatureVals() {
        return FeatureVals;
    }
}

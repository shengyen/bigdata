package tw.edu.cgu.im;
import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

import javax.swing.JOptionPane;
public class HW1 {

	public static void main(String[] args) throws IOException, TasteException {
		// TODO Auto-generated method stub
		//Recommender
		DataModel model = new FileDataModel(new File("data/ratings.csv"));
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		
		String ss1 = JOptionPane.showInputDialog("請輸入ID：");
		int id = Integer.parseInt(ss1);
		String ss2 = JOptionPane.showInputDialog("請輸入推薦數量：");
		int quantity = Integer.parseInt(ss2);
		java.util.List<RecommendedItem> recommendations = recommender.recommend(id, quantity);
		for (RecommendedItem recommendation : recommendations){
			System.out.println(recommendation);
		}
		JOptionPane.showMessageDialog(null,recommendations);
	    //Evaluation
		RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
		RecommenderBuilder builder = new MyRecommenderBuilder();
		double result = evaluator.evaluate(builder, null, model, 0.9, 1.0);		
		System.out.println(result);
			
		
		
		JOptionPane.showMessageDialog(null,"Evaluation:"+ result);
	}
}

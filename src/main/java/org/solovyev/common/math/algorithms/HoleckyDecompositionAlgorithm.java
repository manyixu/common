package org.solovyev.common.math.algorithms;

import org.solovyev.common.math.graph.Graph;
import org.solovyev.common.math.matrix.*;

import java.io.*;

/**
 * User: SerSo
 * Date: 15.04.2009
 * Time: 11:43:56
 */
public class HoleckyDecompositionAlgorithm extends AbstractAlgorithm<HoleckyDecompositionAlgorithm.Input, Matrix<Double>> {

	public static class Input {
		private final Matrix<Double> m;

		public Input(Matrix<Double> m) {
			this.m = m;
		}
	}


	public Matrix<Double> doAlgorithm() {
		CutHillMcKeeAlgorithm kma = new CutHillMcKeeAlgorithm();
		kma.init(new CutHillMcKeeAlgorithm.Input(new Graph<Object, Double>(input.m), false, false));
		kma.doAlgorithm();
		Matrix<Double> tmp = new DoubleArrayMatrix(kma.getResult());
		result = new DoubleArrayMatrix(tmp.getNumberOfRows(), tmp.getNumberOfColumns());
		Double value;
		for (int i = 0; i < tmp.getNumberOfRows(); i++) {
			//todo serso: use faster
			//setting left to diagonal elements
			for (int j = 0; j < i; j++) {
				value = 0d;
				for (int k = 0; k < j; k++) {
					value += result.get(i, k) * result.get(j, k);
				}
				result.set(i, j, (tmp.get(i, j) - value) / result.get(j, j));
				//l.set(j, i, l.get(i, j));
			}

			//setting diagonal element
			value = 0d;
			for (int k = 0; k < i; k++) {
				value += result.get(i, k) * result.get(i, k);
			}
			result.set(i, i, Math.sqrt(tmp.get(i, i) - value));
		}

		return result;
	}

	public static void main(String[] arg) {
		try {
			if (arg != null && arg.length > 0) {
				PrintWriter out = new PrintWriter(System.out, true);
				Matrix<Double> m = new DoubleArrayMatrix(arg[0], MatrixFileFormat.valueOf(arg[1].toUpperCase()));
				HoleckyDecompositionAlgorithm hda = new HoleckyDecompositionAlgorithm();
				hda.init(new Input(m));
				hda.doAlgorithm();
				Matrix<Double> l = hda.getResult();
				Matrix<Double> lt = MatrixUtils.getTransposeMatrix(l);
				Matrix<Double> llt = MatrixUtils.multiply(l, lt);
				out.write("Is same:" + MatrixUtils.isSame(m, llt, 100d));
				out.println();
			}
		} catch (IOException e) {
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter("log.txt"));
				out.write("Internal error while opening file.");
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}
}

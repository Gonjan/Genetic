
public class MainTest {
	public static void main(String[] args) {

		// ѡ��һ�������Ļ������С���������Լ����ⶨ
		FitnessCalc
				.setSolution("11110000000000000000000000000000000000000000000000000000001111");

		// ��ʼ��һ����Ⱥ
		Population myPop = new Population(50, true);

		// ���ε��������н��������� ֱ���ҵ������Ļ�������
		int generationCount = 0;
		while (myPop.getFittest().getFitness() < FitnessCalc.getMaxFitness()) {
			generationCount++;
			System.out.println("Generation: " + generationCount + " Fittest: "
					+ myPop.getFittest().getFitness());
			myPop = Algorithm.evolvePopulation(myPop);
		}
		System.out.println("Solution found!");
		System.out.println("Generation: " + generationCount);
		System.out.println("Final Fittest Genes:");
		System.out.println(myPop.getFittest());

		
	}
}
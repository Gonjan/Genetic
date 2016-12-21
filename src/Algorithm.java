public class Algorithm {

    /* GA �㷨�Ĳ��� */
    private static final double uniformRate = 0.5; //�������
    private static final double mutationRate = 0.015; //ͻ�����
    private static final int tournamentSize = 5; //��̭����Ĵ�С
    private static final boolean elitism = true; //��Ӣ����

    // ����һ����Ⱥ
    public static Population evolvePopulation(Population pop) {
    	// �����һ������Ⱥ
        Population newPopulation = new Population(pop.size(), false);

        // ����������Ǹ� ���ڵ�һ��λ��. 
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
        	//���ѡ������ ����ĸ���
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            //���н���
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population  ͻ��
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    // ������������Ľ��� (��������Ϊmake love�Ĺ��̰�)�� ����ĸ���ΪuniformRate
    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();
        // ����Ĵ� ����������ѡ�� 
        for (int i = 0; i < indiv1.size(); i++) {
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    // ͻ����塣 ͻ��ĸ���Ϊ mutationRate
    private static void mutate(Individual indiv) {
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                // ��������� 0 �� 1
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i, gene);
            }
        }
    }

    // ���ѡ��һ��������ĸ��壬���˽��н���
    private static Individual tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournamentPop = new Population(tournamentSize, false);
        //���ѡ�� tournamentSize ������ tournamentPop ��
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournamentPop.saveIndividual(i, pop.getIndividual(randomId));
        }
        // �ҵ���̭�������������
        Individual fittest = tournamentPop.getFittest();
        return fittest;
    }
}
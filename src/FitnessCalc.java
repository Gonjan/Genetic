
public class FitnessCalc {

    static byte[] solution = new byte[64];

    /* Public methods */
    // ���ú�ѡ���Ϊһ�� byte array
    public static void setSolution(byte[] newSolution) {
        solution = newSolution;
    }

    // ���ǰ�01 �ַ���ת��Ϊ 01���飬 ���� solution��
    static void setSolution(String newSolution) {
        solution = new byte[newSolution.length()];
        // Loop through each character of our string and save it in our byte 
        for (int i = 0; i < newSolution.length(); i++) {
            String character = newSolution.substring(i, i + 1);
            if (character.contains("0") || character.contains("1")) {
                solution[i] = Byte.parseByte(character);
            } else {
                solution[i] = 0;
            }
        }
    }

    // ͨ����solution�Ƚ� ������������Ӧֵ
    static int getFitness(Individual individual) {
        int fitness = 0;
        for (int i = 0; i < individual.size() && i < solution.length; i++) {
            if (individual.getGene(i) == solution[i]) {
                fitness++;
            }
        }
        return fitness;
    }

    //���ŵ���Ӧֵ����Ϊ�������еĳ���
    static int getMaxFitness() {
        int maxFitness = solution.length;
        return maxFitness;
    }
}
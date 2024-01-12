import java.util.ArrayList;
import java.util.Scanner;

public class amestest {
    public static void main(String[] args) {

        /////////////////////////////////////////////
        // Variable Declaration and Initialization //
        /////////////////////////////////////////////

        // User Input //
        Scanner userInput = new Scanner(System.in);
        System.out.print("Input the 5\'--> 3\' DNA Sequence: ");
        String dnaSequence = userInput.nextLine();
        System.out.print("Input the number of stop codons: ");
        int numberOfStops = userInput.nextInt();
        ArrayList<String> stopCodons = new ArrayList<>();

        // Stop codon storage and processing
        for (int i = 0; i < numberOfStops; i++) {
            System.out.print("Enter stop codon " + (i+1) + ": ");
            String inputCodon = userInput.next();
            String processedStop = "";
            // Convert U's to T's to get recognized on DNA strand
            for (int j = 0; j < inputCodon.length(); j++) {
                if (inputCodon.charAt(j) == 'U') {
                    processedStop += 'T';
                }
                else {
                    processedStop += inputCodon.charAt(j);
                }
            }
            stopCodons.add(processedStop);
        }
        // Substitution selector
        System.out.println("Enter:\n1 for A <--> G\n2 for C <--> T\n3 For Both");
        int substituionSelector = userInput.nextInt();
        ArrayList<Integer> mutatedCodonStops = new ArrayList<>();
        ArrayList<Integer> mutatedNucleotideStops = new ArrayList<>();


        /////////////////////
        // Mutation Checker//
        /////////////////////

        // Iterate different codons
        for (int codonNumber = 1; codonNumber < dnaSequence.length()/3; codonNumber++) {
            String currCodon;
            if (codonNumber == dnaSequence.length()/3) {
                currCodon = dnaSequence.substring((codonNumber*3)-3);
            }
            else {
                currCodon = dnaSequence.substring((codonNumber*3)-3, (codonNumber*3));
            }
            // Inside each codon, iterate nucleotides
            for (int i = 0; i < 3; i++) {
                char currNucleotide = currCodon.charAt(i);
                char mutatedNucleotide;
                String mutatedCodon = currCodon;


                // A <--> G
                if (substituionSelector == 1) {
                    // A --> G
                    if (currNucleotide == 'A') {
                        mutatedNucleotide = 'G';
                        mutatedCodon = codonMutator(mutatedNucleotide, currCodon, currNucleotide, i);
                    }
                    // G --> A
                    else if (currNucleotide == 'G') {
                        mutatedNucleotide = 'A';
                        mutatedCodon = codonMutator(mutatedNucleotide, currCodon, currNucleotide, i);
                    }
                    if (stopCodons.contains(mutatedCodon)) {
                        mutatedCodonStops.add(codonNumber);
                        mutatedNucleotideStops.add(i);
                    }
                }
                

                // C <--> T
                else if (substituionSelector == 2) {
                    // C --> T
                    if (currNucleotide == 'C') {
                        mutatedNucleotide = 'T';
                        mutatedCodon = codonMutator(mutatedNucleotide, currCodon, currNucleotide, i);
                    }
                    // T --> C
                    else if (currNucleotide == 'T') {
                        mutatedNucleotide = 'C';
                        mutatedCodon = codonMutator(mutatedNucleotide, currCodon, currNucleotide, i);
                    }
                    if (stopCodons.contains(mutatedCodon)) {
                        mutatedCodonStops.add(codonNumber);
                        mutatedNucleotideStops.add(i);
                    }
                }


                // Both C <--> T and A <--> G
                else if (substituionSelector == 3) {
                    // A --> G
                    if (currNucleotide == 'A') {
                        mutatedNucleotide = 'G';
                        mutatedCodon = codonMutator(mutatedNucleotide, currCodon, currNucleotide, i);
                    }
                    // G --> A
                    else if (currNucleotide == 'G') {
                        mutatedNucleotide = 'A';
                        mutatedCodon = codonMutator(mutatedNucleotide, currCodon, currNucleotide, i);
                    }
                    // C --> T
                    else if (currNucleotide == 'C') {
                        mutatedNucleotide = 'T';
                        mutatedCodon = codonMutator(mutatedNucleotide, currCodon, currNucleotide, i);
                    }
                    // T --> C
                    else if (currNucleotide == 'T') {
                        mutatedNucleotide = 'C';
                        mutatedCodon = codonMutator(mutatedNucleotide, currCodon, currNucleotide, i);
                    }
                    if (stopCodons.contains(mutatedCodon)) {
                        mutatedCodonStops.add(codonNumber);
                        mutatedNucleotideStops.add(i+1);
                    }
                }
            }
        }
        System.out.println("Mutations triggered stop codons in the following: ");
        System.out.println("Codon Number: " + mutatedCodonStops);
        System.out.println("Nucleotide Number in Codon: " + mutatedNucleotideStops);
        userInput.close();
    }
    
    ///////////////////
    // Codon mutator //
    ///////////////////
    public static String codonMutator(char mutatedNucleotide, String currCodon, int currNucleotide, int i) {
        String mutatedCodon = "";
        if (currNucleotide == 2) {
            mutatedCodon = currCodon.substring(0,2) + mutatedNucleotide;
        }
        else {
            mutatedCodon = currCodon.substring(0,i) + mutatedNucleotide + currCodon.substring(i+1);
        }
        return mutatedCodon;
    }
}

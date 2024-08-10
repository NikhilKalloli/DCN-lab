import java.util.Scanner;

public class LeakyBucket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter bucket Capacity and rate: ");
        int bucket_cap = sc.nextInt();  // Bucket capacity
        int rate = sc.nextInt();        // Rate at which the bucket leaks

        System.out.print("\nEnter no. of packets: ");
        int n = sc.nextInt();           // Number of packets
        int[] packets = new int[n];     // Array to store packet sizes
        System.out.println("Enter size of packets:");
        for (int i = 0; i < n; ++i) {
            System.out.print("\tpacket[" + (i + 1) + "]: ");
            packets[i] = sc.nextInt();  // Reading packet sizes
        }

        int current_bucket = 0;  // Remaining capacity in the bucket

        for (int i = 0; i < n; ++i) {
            int packetSize = packets[i];
            boolean packetDropped = false;
            
            // Check if the packet can be added to the bucket
            if (packetSize + current_bucket > bucket_cap) {
                packetDropped = true;
            } else {
                current_bucket += packetSize;
            }

            // Determine the amount of data to send out
            int sent = Math.min(current_bucket, rate);
            current_bucket -= sent;

            // Print the results
            System.out.println("\nPacket[" + (i + 1) + "]: " + packetSize);
            if (packetDropped) {
                System.out.println("Packet dropped!!");
            } else {
                System.out.println("Received: " + packetSize);
                System.out.println("Sent: " + sent);
                System.out.println("Remaining: " + current_bucket);
            }
        }
        
        sc.close();
    }
}

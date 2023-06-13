import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class List
{
    static Node l;
    public static void addRec(Data r)
    {
        Node newNode = new Node();
        newNode.rec = r;

        if(l == null)
        {
            l = newNode;
        }
        else
        {
            Node p = l;
            while(p.next != null)
            {
                p = p.next;
            }
            p.next = newNode;
        }
    }

    public static void displayDB()
    {
        Node p = l;
        int i = 1;
        while(p != null)
        {
            System.out.print(i + ".|");
            System.out.print("\t" + p.rec.name);
            System.out.print("\t\t" + p.rec.course);
            System.out.print("\t\t" + p.rec.yrSec);
            System.out.print("\t\t" + p.rec.schoolID);
            System.out.print("\t\t" + p.rec.address);
            System.out.print("\t\t" + p.rec.contactNum + "\n");
            p = p.next;
            i++;
        }
    }

    public static void saveDB()
    {
        Node p = l;
        try(FileWriter fp = new FileWriter("mainDB.txt"))
        {
            while(p != null){
                fp.write(p.rec.name + "\t" + p.rec.course + "\t" + p.rec.yrSec + "\t" + p.rec.schoolID + "\t" +
                         p.rec.contactNum + "\t" + p.rec.address + "\n");
                p = p.next;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void retrieveDB()
    {
        try(FileReader fp = new FileReader("mainDB.txt"))
        {
            BufferedReader br = new BufferedReader(fp);
            String line;

            while ((line = br.readLine()) != null)
            {
                Data rec = new Data();
                String[] data = line.split("\t");
                rec.name = data[0].trim();
                rec.course = data[1].trim();
                rec.yrSec = data[2].trim();
                rec.schoolID = data[3].trim();
                rec.contactNum = data[4].trim();
                rec.address = data[5].trim();
                addRec(rec);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}

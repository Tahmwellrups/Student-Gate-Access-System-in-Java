import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class List
{
    public static Node l;
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

    public static boolean regCheck(Data r)
    {
        if (l == null) //If it is empty, proceed to the registration
        {
            return true;
        }
        else
        {
            Node p = l;
            while (p != null)
            {
                if(p.rec.schoolID.equals(r.schoolID))//If the school ID, is already existing then do not register
                {
                    return false;
                }
                p = p.next;
            }
        }
        return true;
    }

    public static void saveDB()
    {
        Node p = l;
        try(FileWriter fp = new FileWriter("mainDB.txt"))
        {
            while(p != null){
                String name = AdminProgram.encryption.encrypt(p.rec.name);
                String course = AdminProgram.encryption.encrypt(p.rec.course);
                String yrSec = AdminProgram.encryption.encrypt(p.rec.yrSec);
                String schoolID = AdminProgram.encryption.encrypt(p.rec.schoolID);
                String contactNum = AdminProgram.encryption.encrypt(p.rec.contactNum);
                String address = AdminProgram.encryption.encrypt(p.rec.address);
                fp.write(name + "\t" + course + "\t" + yrSec + "\t" + schoolID + "\t" +
                         contactNum + "\t" + address + "\n");
                p = p.next;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try(FileWriter fp = new FileWriter("C:IdeaProjects//AccessSystem//mainDB.txt"))
        {
            while(p != null){
                String name = AdminProgram.encryption.encrypt(p.rec.name);
                String course = AdminProgram.encryption.encrypt(p.rec.course);
                String yrSec = AdminProgram.encryption.encrypt(p.rec.yrSec);
                String schoolID = AdminProgram.encryption.encrypt(p.rec.schoolID);
                String contactNum = AdminProgram.encryption.encrypt(p.rec.contactNum);
                String address = AdminProgram.encryption.encrypt(p.rec.address);
                fp.write(name + "\t" + course + "\t" + yrSec + "\t" + schoolID + "\t" +
                        contactNum + "\t" + address + "\n");
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
    public static void saveToFD(String ID) {
        try (FileWriter fp = new FileWriter("D:/studentID.txt")) {
            fp.write(ID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tyler Auslitz
 */
public class Main {
    //private static File instructions;
    private static File inputFile;
    private static File instructionFile, dataFile;
    private static final String folder=("D:\\Logic Gates\\Pong Wall\\"),fHeader="v2.0 raw";
    private static String input="",opCodes,data,toData=fHeader+"",toInstructions=fHeader+"";
    private static final String[] opcodeHeader={"mov","jmp","cmp","add","shl","sub","shr","jle","jne","jg","jeq","inc","dec","rea"};
    private static String insOpCode="";
    private static final char[] registers={'a','b','c','d'};
    public static void main(String[] args) {
        //instructions=new File(Main.class.getResource("/src/src/instructions.txt").getFile());
        inputFile=new File(folder + "pongwall.asm");
        try {
            BufferedReader reader=new BufferedReader(new FileReader(inputFile));
            String r="";
            while((r=reader.readLine())!=null)
            {
                input+=r+"\n";
            }
            /*reader=new BufferedReader(new FileReader(instructions));
            r="";
            while((r=reader.readLine())!=null)
            {
                insOpCode+=r+"\n";
            }*/
            assemble3();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            
        }
        
        
    }
    private static void assemble3()//include comments and labels
    {
        ArrayList labels=new ArrayList<Label>();
        String instructions[]=input.split("\n");
        for(int i=0;i<instructions.length;i++)
        {
            System.out.println(instructions[i]);
            if(instructions[i].contains(":"))
            {
                labels.add(new Label(instructions[i].split(":")[0],i));
            }
            else if(instructions[i].contains(";"))
            {
                String temp[]=instructions[i].split(";");
                instructions[i]=temp[0];
            }
        }
        for(int i=0;i<instructions.length;i++)
        {
            String tempHolder[]=hasInstruction(instructions[i]);
            if(!tempHolder[0].equals("?"))
            {
                toInstructions+=tempHolder[0];
                toData+=tempHolder[1];
            }
        }
    }
    private static String[] hasInstruction(String in)
    {
        String out[]={"?",""};
        boolean moveValue=false;
        if(!in.contains(",") && !in.contains("rea"))return out;
        out[0]="";
        String temp[];
        if(in.contains("mov"))
        {
            temp=in.split(",");
            if(temp[0].contains("a"))
            {
                if(temp[1].contains("[d]"))out[0]+="1 ";
                else if(temp[1].contains("b"))out[0]+="2 ";
                else if(temp[1].contains("c"))out[0]+="3 ";
                else if(temp[1].contains("d"))out[0]+="4 ";
                else 
                {
                    int num=Integer.parseInt(temp[1],16);
                    out[0]+="17 ";
                    out[1]+=num + " ";
                    moveValue=true;
                }
                if(!moveValue)out[1]="00 ";
            }
            else if(temp[0].contains("b"))
            {
                if(temp[1].contains("[d]"))out[0]+="5 ";
                else if(temp[1].contains("a"))out[0]+="6 ";
                else if(temp[1].contains("c"))out[0]+="7 ";
                else if(temp[1].contains("d"))out[0]+="8 ";
                else 
                {
                    int num=Integer.parseInt(temp[1],16);
                    out[0]+="18 ";
                    out[1]+=num + " ";
                    moveValue=true;
                }
                if(!moveValue)out[1]="00 ";
            }
            else if(temp[0].contains("c"))
            {
                if(temp[1].contains("[d]"))out[0]+="9 ";
                else if(temp[1].contains("a"))out[0]+="a ";
                else if(temp[1].contains("b"))out[0]+="b ";
                else if(temp[1].contains("d"))out[0]+="c ";
                else 
                {
                    int num=Integer.parseInt(temp[1],16);
                    out[0]+="19 ";
                    out[1]+=num + " ";
                    moveValue=true;
                }
                if(!moveValue)out[1]="00 ";
            }
            else if(temp[0].contains("d"))
            {
                if(temp[1].contains("a"))out[0]+="d ";
                else if(temp[1].contains("b"))out[0]+="e ";
                else if(temp[1].contains("c"))out[0]+="f ";
                else 
                {
                    int num=Integer.parseInt(temp[1],16);
                    out[0]+="1a ";
                    out[1]+=num + " ";
                    moveValue=true;
                }
                if(!moveValue)out[1]="00 ";
            }
            else if(temp[0].contains("[d]"))
            {
                if(temp[1].contains("a"))out[0]+="10 ";
                else if(temp[1].contains("b"))out[0]+="11 ";
                else if(temp[1].contains("c"))out[0]+="12 ";
            }
        }
        else if(in.contains("add"))
        {
            temp=in.split(",");
            if(temp[0].contains("a"))
            {
                if(temp[1].contains("a"))out[0]+="13 ";
                else if(temp[1].contains("b"))out[0]+="14 ";
                else if(temp[1].contains("c"))out[0]+="15 ";
                else if(temp[1].contains("d"))out[0]+="16 ";
            }
        }
        else if(in.contains("sub"))
        {
            temp=in.split(",");
            if(temp[0].contains("a"))
            {
                if(temp[1].contains("b"))out[0]+="1b ";
                else if(temp[1].contains("c"))out[0]+="1c ";
                else if(temp[1].contains("d"))out[0]+="1d ";
            }
        }
        else if(in.contains("cmp"))
        {
            
        }
        else if(in.contains("jmp"))
        {
            
        }
        else if(in.contains("jeq"))
        {
            
        }
        else if(in.contains("jne"))
        {
            
        }
        else if(in.contains("jl"))
        {
            
        }
        else if(in.contains("jg"))
        {
            
        }
        else if(in.contains("inc"))
        {
            
        }
        else if(in.contains("dec"))
        {
            
        }
        else if(in.contains("shr"))
        {
            
        }
        else if(in.contains("shl"))
        {
            
        }
        else if(in.contains("rea"))
        {
            
        }
        return out;
    }
    private static boolean hasLabel(int line, ArrayList<Label> in)
    {
        for(int i=0;i<in.size();i++)
        {
            if(in.get(i).getLine()==line)return true;
        }
        return false;
    }
    private static void assemble2()//opcodes are an array of opcode headers
    {
        String instructions[]=input.split("\n");
        String outputText="",outputData="";
        int eightThing=1;
        for(int i=0;i<instructions.length;i++)
        {
            String instructionOp;
            String[] instructionParam=instructions[i].split(",");
            System.out.println(instructions[i]);
            char lastChar0=' ',lastChar1=' ';
            if(!instructions[i].contains(","))
            {
                instructionParam=new String[2];
                instructionParam[0]=" ";
                instructionParam[1]="00 ";
                if(instructions[i].contains("rea"))
                {
                    if(instructions[i].charAt(instructions[i].length()-1)=='a')outputText+="49 ";
                    else err(i+1);
                    outputData+="00 ";
                }
            }
            try{
                lastChar0=instructionParam[0].charAt(instructionParam[0].length()-1);
                lastChar1=instructionParam[1].charAt(0);
            }
            catch(ArrayIndexOutOfBoundsException ex){}
            if(instructions[i].contains("mov"))
            {
                System.out.println(lastChar0+","+lastChar1);
                if(lastChar0==']')//memory instruction
                {
                    if(lastChar1=='a')outputText+="10 ";
                    else if(lastChar1=='b')outputText+="11 ";
                    else if(lastChar1=='c')outputText+="12 ";
                    else err(i+1);
                    outputData+="00 ";
                }
                else if(lastChar0=='}')//video memory instruction
                {
                    if(lastChar1=='[')outputText+="34 ";
                    else if(lastChar1=='a')outputText+="35 ";
                    else if(lastChar1=='c')outputText+="36 ";
                    else err(i+1);
                    outputData+="00 ";
                }
                else if(isRegister(lastChar0))
                {
                    boolean outZero=true;
                    String pass=" ";
                    if(lastChar0=='a')
                    {
                        if(lastChar1=='[')outputText+="01 ";
                        else if(lastChar1=='b')outputText+="02 ";
                        else if(lastChar1=='c')outputText+="03 ";
                        else if(lastChar1=='d')outputText+="04 ";
                        else 
                        {
                            pass="17 ";
                            outZero=false;
                        }
                    }
                    else if(lastChar0=='b')
                    {
                        if(lastChar1=='[')outputText+="05 ";
                        else if(lastChar1=='a')outputText+="06 ";
                        else if(lastChar1=='c')outputText+="07 ";
                        else if(lastChar1=='d')outputText+="08 ";
                        else 
                        {
                            pass="18 ";
                            outZero=false;
                        }
                    }
                    else if(lastChar0=='c')
                    {
                        if(lastChar1=='[')outputText+="09 ";
                        else if(lastChar1=='a')outputText+="0a ";
                        else if(lastChar1=='b')outputText+="0b ";
                        else if(lastChar1=='d')outputText+="0c ";
                        else 
                        {
                            pass="19 ";
                            outZero=false;
                        }
                    }
                    else if(lastChar0=='d')
                    {
                        if(lastChar1=='a')outputText+="0d ";
                        else if(lastChar1=='b')outputText+="0e ";
                        else if(lastChar1=='c')outputText+="0f ";
                        else 
                        {
                            pass="1a ";
                            outZero=false;
                        }
                    }
                    else 
                    {
                        
                    }
                    if(outZero)outputData+="00 ";
                    else 
                    {
                        outputText+=pass;
                        outputData+=instructionParam[1] + " ";
                    }
                }
            }
            else if(instructions[i].contains("jmp"))
            {
                if(lastChar0==']')
                {
                    outputText+="2a ";
                    outputData+="00 ";
                }
                else
                {
                    outputText+="2b ";
                    for(int k=4;k<instructions[i].length();k++)
                    {
                        outputData+=instructions[i].charAt(k)+"";
                    }
                    outputData+=" ";
                }
            }
            else if(instructions[i].contains("jeq"))
            {
                if(lastChar0==']')
                {
                    outputText+="2d ";
                    outputData+="00 ";
                }
                else
                {
                    outputText+="2c ";
                    for(int k=4;k<instructions[i].length();k++)
                    {
                        outputData+=instructions[i].charAt(k)+"";
                    }
                    outputData+=" ";
                }
            }
            else if(instructions[i].contains("jne"))
            {
                if(lastChar0==']')
                {
                    outputText+="2e ";
                    outputData+="00 ";
                }
                else
                {
                    outputText+="2f ";
                    for(int k=4;k<instructions[i].length();k++)
                    {
                        outputData+=instructions[i].charAt(k)+"";
                    }
                    outputData+=" ";
                }
            }
            else if(instructions[i].contains("jl"))
            {
                if(lastChar0==']')
                {
                    outputText+="30 ";
                    outputData+="00 ";
                }
                else
                {
                    outputText+="31 ";
                    for(int k=4;k<instructions[i].length();k++)
                    {
                        outputData+=instructions[i].charAt(k)+"";
                    }
                    outputData+=" ";
                }
            }
            else if(instructions[i].contains("jg"))
            {
                if(lastChar0==']')
                {
                    outputText+="32 ";
                    outputData+="00 ";
                }
                else
                {
                    outputText+="33 ";
                    for(int k=4;k<instructions[i].length();k++)
                    {
                        outputData+=instructions[i].charAt(k)+"";
                    }
                    outputData+=" ";
                }
            }
            else if(instructions[i].contains("add"))
            {
                if(lastChar0=='a')
                {
                    if(lastChar1=='a')outputText+="13 ";
                    else if(lastChar1=='b')outputText+="14 ";
                    else if(lastChar1=='c')outputText+="15 ";
                    else if(lastChar1=='d')outputText+="16 ";
                    else err(i+1);
                    outputData+=instructionParam[1] + " ";
                }
            }
            else if(instructions[i].contains("sub"))
            {
                if(lastChar0=='a')
                {
                    if(lastChar1=='b')outputText+="1b ";
                    else if(lastChar1=='c')outputText+="1c ";
                    else if(lastChar1=='d')outputText+="1d ";
                    else err(i+1);
                    outputData+=instructionParam[1] + " ";
                }
            }
            else if(instructions[i].contains("cmp"))
            {
                if(lastChar0=='a')
                {
                    if(lastChar1=='b')outputText+="1e ";
                    else if(lastChar1=='c')outputText+="1f ";
                    else if(lastChar1=='d')outputText+="20 ";
                    else err(i+1);
                    outputData+="00 ";
                }
            }
            else if(instructions[i].contains("inc"))
            {
                char last=instructions[i].charAt(instructions[i].length()-1);
                if(last=='a')outputText+="39 ";
                else if(last=='b')outputText+="3a ";
                else if(last=='c')outputText+="3b ";
                else if(last=='d')outputText+="3c ";
                else err(i+1);
                outputData+="00 ";
            }
            else if(instructions[i].contains("dec"))
            {
                char last=instructions[i].charAt(instructions[i].length()-1);
                if(last=='a')outputText+="3d ";
                else if(last=='b')outputText+="3e ";
                else if(last=='c')outputText+="3f ";
                else if(last=='d')outputText+="40 ";
                else err(i+1);
                outputData+="00 ";
            }
            else if(instructions[i].contains("shr"))
            {
                if(lastChar0=='a')outputText+="41 ";
                else if(lastChar0=='b')outputText+="42 ";
                else if(lastChar0=='c')outputText+="43 ";
                else if(lastChar0=='d')outputText+="44 ";
                else err(i+1);
                outputData+=instructionParam[1] + " ";
            }
            else if(instructions[i].contains("shl"))
            {
                if(lastChar0=='a')outputText+="45 ";
                else if(lastChar0=='b')outputText+="46 ";
                else if(lastChar0=='c')outputText+="47 ";
                else if(lastChar0=='d')outputText+="48 ";
                else err(i+1);
                outputData+=instructionParam[1] + " ";
            }
            else if(instructions[i].contains("rea"))
            {
//                System.err.println(lastChar0);
//                if(lastChar0=='a')outputText+="49 ";
//                else err(i+1);
//                outputData+="00 ";
            }
            if(eightThing==8)
            {
                eightThing=1;
                String temp="";
                for(int l=0;l<outputData.length()-1;l++)
                {
                    temp+=outputData.charAt(l);
                }
                outputData=temp+"\n";
                temp="";
                for(int l=0;l<outputText.length()-1;l++)
                {
                    temp+=outputText.charAt(l);
                }
                outputText=temp+"\n";
            }
            eightThing++;
        }
        instructionFile=new File(folder + "cpuinstruction.txt");
        dataFile=new File(folder + "data.txt");
        try {
            BufferedWriter out=new BufferedWriter(new FileWriter(instructionFile));
            out.write(fHeader+"\n"+outputText+"\n");
            out.close();
            System.out.println("Instruction file written");
            out=new BufferedWriter(new FileWriter(dataFile));
            out.write(fHeader+"\n"+outputData+"\n");
            out.close();
            System.out.println("Data File written");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void err(int line)
    {
        System.err.println("Error on line " + (line));
        System.exit(1);
    }
    private static boolean isRegister(char in)
    {
        for(int i=0;i<registers.length;i++)
        {
            if(in==registers[i])return true;
        }
        return false;
    }
    private static void assemble()
    {
        opCodes="v2.0 raw";
        data="v2.0 raw";
        final char[] registers={'a','b','c','d'};
        String[] instruction=input.split("\n");
        //String[] insOpCod=insOpCode.split("\n");
        for(int i=0;i<instruction.length;i++)
        {
            System.out.println(instruction[i]);
            if(instruction[i].substring(0, 3).equals("mov"))
            {
                String[] temp=instruction[i].split(",");
                char beforeComa=temp[0].charAt(temp[0].length()-1);
                System.out.println(beforeComa + "," + temp[1]);
                if(testForRegisters(beforeComa) && testForRegisters(temp[1]))
                {
                    for(int character=0;character<registers.length;character++)
                    {
                        if(beforeComa==registers[character])
                        {
                            
                        }
                    }
                }
                else if(testForRegisters(beforeComa) && !testForRegisters(temp[1]))
                {
                    
                }
                    
            }
        }
    }
    
    private static boolean testForRegisters(char in)
    {
        if(in=='a' || in=='b' || in=='c' || in=='d')
        {
            return true;
        }
        else return false;
    }
    private static boolean testForRegisters(String in)
    {
        return "a".equals(in) || "b".equals(in) || "c".equals(in) || "d".equals(in);
    }
}

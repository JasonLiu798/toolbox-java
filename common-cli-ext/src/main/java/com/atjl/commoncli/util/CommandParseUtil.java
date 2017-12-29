package com.atjl.commoncli.util;


import com.atjl.commoncli.domain.CmdDomain;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.file.FileUtil;
import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;

public class CommandParseUtil {

    public static CmdDomain preParse(String line) {
        if (StringCheckUtil.isEmpty(line)) {
            return null;
        }
        String[] parts = line.split(" ");
        if (CollectionUtil.isEmpty(parts)) {
            return null;
        }
        CmdDomain cmd = new CmdDomain();
        cmd.setCmd(parts[0]);
        if (parts.length > 1) {
            String[] opts = new String[parts.length - 1];
            System.arraycopy(parts, 1, opts, 0, opts.length);
            cmd.setOpts(opts);
            return cmd;
        } else {
            return cmd;
        }
    }


    public static List<String> lsParser(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
//        options.addOption("h", "help", false, "Print this usage information");
        options.addOption("a", "all", false, "do not hide entries starting with .");
        options.addOption("l", false, "show ctime and sort by name otherwise: sort by ctime");
        options.addOption("A", "almost-all", false, "do not list implied . and ..");
        options.addOption("b", "escape", false, "print octal escapes for nongraphic characters");
        options.addOption(OptionBuilder.withLongOpt("block-size")
                .withDescription("use SIZE-byte blocks")
                .hasArg()
                .withArgName("SIZE")
                .create());
        options.addOption("B", "ignore-backups", false, "do not list implied entried "
                + "ending with ~");
        options.addOption("c", false, "with -lt: sort by, and show, ctime (time of last modification of file status information) ");
        options.addOption("C", false, "list entries by columns");
        options.addOption("h", false, "human read capacity");

        // Parse the program arguments
        CommandLine line = null;
        line = parser.parse(options, args);

        // Set the appropriate variables based on supplied options
        boolean verbose = false;
        String file = "";

        if (line.hasOption('h')) {
            System.out.println("Help Message");
//            List<String> help = new ArrayList<>();
            return CollectionUtil.newList(options.toString());
//            return help;
        }
        if (line.hasOption("block-size")) {
            // print the value of block-size
//            System.out.println("block size:" + line.getOptionValue("block-size"));
            return CollectionUtil.newList(line.getOptionValue("block-size"));
        }
        if (line.hasOption("l")) {
            // print the value of block-size
//            System.out.println("block size:" + line.getOptionValue("block-size"));
            if (!CollectionUtil.isEmpty(args) && args.length > 0) {
                return FileUtil.ll(args[args.length - 1]);
            }
            return new ArrayList<>();
//            return CollectionUtil.newList(line.getOptionValue("block-size"));
        }

        if (!CollectionUtil.isEmpty(args) && args.length > 0) {
            return FileUtil.ls(args[args.length - 1]);
        }
        return new ArrayList<>();
    }

}

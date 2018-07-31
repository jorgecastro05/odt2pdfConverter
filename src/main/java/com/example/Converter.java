package com.example;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

import java.io.*;

public class Converter {

    public static void main(String[] args) throws Exception {

        // 1) Load ODT file and set Velocity template engine and cache it to the registry
        InputStream in = Converter.class.getResourceAsStream("/ODTHelloWordWithVelocity.odt");
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

        // 2) Create Java model context
        IContext context = report.createContext();
        context.put("money", 1500);

        // 3) Set PDF as format converter
        Options options = Options.getTo(ConverterTypeTo.PDF);

        // 3) Generate report by merging Java model with the ODT and convert it to PDF
        OutputStream out = new FileOutputStream(new File("/home/enrique/ODTHelloWordWithVelocity_Out.pdf"));
        report.convert(context, options, out);
    }
}

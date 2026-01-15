package com.my_example.service;

import com.my_example.dto.NumerologyDTO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Single Responsibility: Generates PDF reports for numerology readings
 * Dependency Inversion: Depends on abstractions (services) rather than concrete implementations
 */
public class PDFGeneratorService {
    
    private NumerologyInterpretationService interpretationService;
    
    public PDFGeneratorService(NumerologyInterpretationService interpretationService) {
        this.interpretationService = interpretationService;
    }
    
    public String generatePDF(NumerologyDTO numerologyDTO, String username, Date dateOfBirth) throws IOException {
       
        String fileName = "Numerology_Report_" + username.replaceAll("\\s+", "_") + "_" + System.currentTimeMillis() + ".txt";
        String filePath = fileName;
        
        // Generate text-based report (can be converted to PDF or enhanced with iText/Apache PDFBox library)
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            String content = generateReportContent(numerologyDTO, username, dateOfBirth);
            fos.write(content.getBytes());
        }
        
        return filePath;
    }
    
    private String generateReportContent(NumerologyDTO numerologyDTO, String username, Date dateOfBirth) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        StringBuilder report = new StringBuilder();
        
        report.append("═══════════════════════════════════════════════════════════\n");
        report.append("                    NUMEROLOGY REPORT\n");
        report.append("═══════════════════════════════════════════════════════════\n\n");
        report.append("Name: ").append(username).append("\n");
        report.append("Date of Birth: ").append(sdf.format(dateOfBirth)).append("\n");
        report.append("Report Generated: ").append(sdf.format(new Date())).append("\n\n");
        
        report.append("═══════════════════════════════════════════════════════════\n");
        report.append("                    YOUR NUMBERS\n");
        report.append("═══════════════════════════════════════════════════════════\n\n");
        report.append("Life Path Number: ").append(numerologyDTO.getLifePathNumber()).append("\n");
        report.append("Destiny Number: ").append(numerologyDTO.getDestinyNumber()).append("\n");
        report.append("Soul Number: ").append(numerologyDTO.getSoulNumber()).append("\n");
        report.append("Personality Number: ").append(numerologyDTO.getPersonalityNumber()).append("\n\n");
        
        report.append("═══════════════════════════════════════════════════════════\n");
        report.append("                    CAREER INSIGHTS\n");
        report.append("═══════════════════════════════════════════════════════════\n\n");
        report.append(interpretationService.getCareerInterpretation(numerologyDTO.getLifePathNumber())).append("\n\n");
        
        report.append("═══════════════════════════════════════════════════════════\n");
        report.append("                 RELATIONSHIP INSIGHTS\n");
        report.append("═══════════════════════════════════════════════════════════\n\n");
        report.append(interpretationService.getRelationshipInterpretation(numerologyDTO.getSoulNumber())).append("\n\n");
        
        report.append("═══════════════════════════════════════════════════════════\n");
        report.append("                    HEALTH INSIGHTS\n");
        report.append("═══════════════════════════════════════════════════════════\n\n");
        report.append(interpretationService.getHealthInterpretation(numerologyDTO.getLifePathNumber())).append("\n\n");
        
        report.append("═══════════════════════════════════════════════════════════\n");
        report.append("                    MONEY INSIGHTS\n");
        report.append("═══════════════════════════════════════════════════════════\n\n");
        report.append(interpretationService.getMoneyInterpretation(numerologyDTO.getDestinyNumber())).append("\n\n");
        
        report.append("═══════════════════════════════════════════════════════════\n");
        report.append("                 CHARACTERISTICS\n");
        report.append("═══════════════════════════════════════════════════════════\n\n");
        report.append(interpretationService.getCharacteristicsInterpretation(numerologyDTO.getLifePathNumber())).append("\n\n");
        
        report.append("═══════════════════════════════════════════════════════════\n");
        report.append("                    END OF REPORT\n");
        report.append("═══════════════════════════════════════════════════════════\n");
        
        return report.toString();
    }
}


package de.stevenschwenke.java.writingawesomejavacodeworkshop.legacy_real_code;

public class HSSFCell {

    private HSSFRichTextString cellValue;
    private HSSFCellStyle cellStyle;

    public void setCellValue(double cellValue) {

    }

    public HSSFRichTextString getCellValue() {
        return cellValue;
    }

    public void setCellStyle(HSSFCellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    public HSSFCellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellValue(String s) {

    }

    public void setCellValue(HSSFRichTextString cellValue) {
        this.cellValue = cellValue;
    }
}

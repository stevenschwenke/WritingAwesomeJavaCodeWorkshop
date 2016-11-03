package de.stevenschwenke.java.writingawesomejavacodeworkshop.legacy_real_code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class has been created by extracting the method "createEachRowDaten" and the needed attributes
 * from real-life legacy code. It serves as a training platform for writing JUnit tests.
 *
 * Things to do here:
 *
 * - Write JUnit tests. You will notice that it's impossible to test the legacy method itself. It
 *   has to be refactored to smaller methods, for which tests have to be written. Use the following
 *   tips like extract variables, constants, methods and classes.
 * - Write JUnit tests that cover extreme parameters as well as expected values and of course null.
 * - Extract wrapper classes for primitive types. For example, often int or Integer is used to
 *   describe a year. java.time.Year is much better suited for that, even if it's just a simple
 *   wrapper around Integer. Try to find primitive types or their boxed equivalents and refactor
 *   them to "good" value objects.
 * - Extract pure functions
 * - remove duplication
 *
 */
class LegacyJUnitTesting {

    private static final Object BLANKCOLUMNWIDTH = 1;

    private static final String DEFAULTTEXT_NOPRODUCT = "X";
    private static final int STARTCOLUMN = 0;
    private static final int QUARTALPROGRAMMDATENCOLUMNWIDTH = 0;

    private final CalenderExcelExportRenderer decorator = null;

    private int eachMonateFirstIndexVonProgrammDaten = 0;

    private final List<Integer> eachMonateFirstIndexVonProgrammDatenList = new ArrayList<Integer>();

    private final Map<String, List<Integer>>
        eachTimeUnitEachFactoryProductCombinationEachMonthFirstIndexVonProgrammDatenMap =
        new HashMap<String, List<Integer>>();

    private final List<Integer> dateColumnList = new ArrayList<Integer>();

    private boolean dateWritten = false;

    private final List<String> writtenFactoryNames = new ArrayList<String>();

    private final List<String> writtenLineNames = new ArrayList<String>();

    private final List<Integer> date1ColumnList = new ArrayList<Integer>();

    private boolean date1Written = false;

    private final Map<String, Integer> factoryProductWeekSumIndex = new HashMap<String, Integer>();

    private final List<Integer> weekSumColumnList = new ArrayList<Integer>();

    private Object workBook;
    private Object MONATPROGRAMMDATENCOLUMNWIDTH = 0;

    private int createEachRowDaten(final HSSFSheet sheet, final FactoryCalendarReportModel modell,
                                   final int startRow, final boolean isProgrammZahlen,
                                   final int endColumn, final int startColumn,
                                   final int startProgrammDatenColumn,
                                   final List<Double> programmDaten, final List<Boolean> date1Data,
                                   final List<Boolean> dates, final HSSFRow row,
                                   final int blockSize, final boolean color,
                                   final List<DayShortDTOImpl> tagesKuerzeln,
                                   final List<Double> wocheSummeProgrammDaten,
                                   final Long produktOID, final Long factoryOID, final Long linieOID) {
        // int startProgrammDatenColumn = getProgrammDatenStartColumn();
        String factoryNames = modell.getFactoryNames().get(factoryOID);
        String linienName = "";
        if (linieOID != null) {
            linienName = modell.getLinienNamen().get(linieOID);
        }
        String produktName = "";
        if (produktOID != null) {
            produktName = modell.getProduktNamen().get(produktOID);

        }
        HSSFCellStyle style;
        int geschriebeneWocheSummeCellAnzahl = 0;

        int monatsSumme = 0;
        for (int i = startColumn; i < endColumn; i++) {
            HSSFCell cell = row.createCell(i);

            boolean firstRow = (row.getRowNum() == startRow) && (blockSize != 1);
            boolean onlyRow = (row.getRowNum() == startRow) && (blockSize == 1);
            boolean lastRow = row.getRowNum() == ((startRow + blockSize) - 1);

            // Clients
            if (i == STARTCOLUMN) {
                if (firstRow) {
                    style = this.decorator.prepareClientCellStyle(getWorkBook(), true, false, false);
                    cell.setCellValue(new HSSFRichTextString(modell.getClientName()));
                } else if (onlyRow) {
                    style = this.decorator.prepareClientCellStyle(getWorkBook(), false, false, true);
                    cell.setCellValue(new HSSFRichTextString(modell.getClientName()));
                } else if (lastRow) {
                    style = this.decorator.prepareClientCellStyle(getWorkBook(), false, true, false);
                    cell.setCellValue(new HSSFRichTextString(modell.getClientName()));
                } else {
                    style =
                        this.decorator
                            .prepareClientCellStyle(getWorkBook(), false, false, false);
                }
                cell.setCellStyle(style);
            }

            // factories
            if (i == (STARTCOLUMN + 1)) {

                if (firstRow) {
                    style = this.decorator.prepareFactoryCellStyle(getWorkBook(), true, false, false);
                    cell.setCellValue(new HSSFRichTextString(factoryNames));
                } else if (onlyRow) {
                    style = this.decorator.prepareFactoryCellStyle(getWorkBook(), false, false, true);
                    cell.setCellValue(new HSSFRichTextString(factoryNames));
                } else if (lastRow) {
                    style = this.decorator.prepareFactoryCellStyle(getWorkBook(), false, true, false);
                }
                // MiddleRow
                else {
                    style = this.decorator.prepareFactoryCellStyle(getWorkBook(), false, false, false);
                    // , true, color);
                }

                if (!this.writtenFactoryNames.contains(factoryNames)) {
                    cell.setCellValue(new HSSFRichTextString(factoryNames));
                    this.writtenFactoryNames.add(factoryNames);

                }
                cell.setCellStyle(style);
                sheet.autoSizeColumn(i);
            }
            // Linien
            if (i == (STARTCOLUMN + 2)) {
                if (firstRow) {
                    style = this.decorator.prepareLinieCellStyle(getWorkBook(), true, false, false);

                    cell.setCellValue(new HSSFRichTextString(linienName));
                } else if (onlyRow) {
                    style = this.decorator.prepareLinieCellStyle(getWorkBook(), false, false, true);
                    cell.setCellValue(new HSSFRichTextString(linienName));
                } else if (lastRow) {
                    style = this.decorator.prepareLinieCellStyle(getWorkBook(), false, true, false);
                }
                // MiddleRow
                else {
                    style =
                        this.decorator
                            .prepareLinieCellStyle(getWorkBook(), false, false, false);
                }

                if (modell.isClientFactoryLinie() || modell.isClientFactoryLinieModell()) {
                    if (!this.writtenLineNames.contains(linienName)) {
                        cell.setCellValue(new HSSFRichTextString(linienName));
                        this.writtenLineNames.add(linienName);
                        sheet.autoSizeColumn(i);
                    }
                }
                cell.setCellStyle(style);
            }

            // Modell
            if ((i == (STARTCOLUMN + 3))) {

                if (firstRow) {
                    style =
                        this.decorator.prepareModellCellStyle(getWorkBook(), true, false,
                                                              false, false, color);
                } else if (onlyRow) {
                    style =
                        this.decorator.prepareModellCellStyle(getWorkBook(), false, false,
                                                              true, false, color);
                } else if (lastRow) {
                    style =
                        this.decorator.prepareModellCellStyle(getWorkBook(), false, true,
                                                              false, false, color);
                } else {
                    style =
                        this.decorator.prepareModellCellStyle(getWorkBook(), false, false,
                                                              false, true, color);
                }

                cell.setCellStyle(style);

                if (!modell.isClientFactory() && !modell.isClientFactoryLinie()) {
                    cell.setCellValue(new HSSFRichTextString(produktName));
                }
                sheet.autoSizeColumn(i);
            }

            // Programmdaten
            if (i >= startProgrammDatenColumn) {
                HSSFCellStyle cellStyle;

                // Quartal
                if (modell.isQuartal()) {
                    // Double daten = programmDaten.get(i - startProgrammDatenColumn);
                    DayShortDTOImpl tk = tagesKuerzeln.get(i - startProgrammDatenColumn);

                    // Client/Factory/Linie/Modell
                    if (modell.isClientFactoryLinieModell() || modell.isClientFactoryModell()) {

                        // Client/Factory/Modell Quartal
                        boolean isDate1 = date1Data.get(i - startProgrammDatenColumn).booleanValue();
                        boolean isDate2 = dates.get(i - startProgrammDatenColumn).booleanValue();

                        // Date between date1 and date2
                        if (!isDate1 && !isDate2) {
                            checkTKNP(cell, tk, "X");
                        }

                        if (isDate1) {
                            cellStyle = this.decorator.getProgrammDatenCellStyle(getWorkBook());
                            this.date1ColumnList.add(new Integer(i));

                            String date1 = "DATE1";
                            date1 = date1.concat(" ").concat(modell.getClientName());
                            cell.setCellStyle(cellStyle);

                            if (!this.date1Written) {
                                cell.setCellValue(new HSSFRichTextString(date1));

                                this.date1Written = true;
                            }
                        }

                        if (isDate2) {
                            cellStyle = this.decorator.getProgrammDatenCellStyle(getWorkBook());
                            this.dateColumnList.add(new Integer(i));

                            String date2 = "DATE2";
                            date2 = date2.concat(" ").concat(modell.getClientName());
                            cell.setCellStyle(cellStyle);

                            if (!this.dateWritten) {
                                cell.setCellValue(new HSSFRichTextString(date2));

                                this.dateWritten = true;
                            }
                        }
                        sheet.setColumnWidth(i, QUARTALPROGRAMMDATENCOLUMNWIDTH);
                    }
                    // Client/Factory Quartal
                    else {
                        checkTKNP(cell, tk, LegacyJUnitTesting.DEFAULTTEXT_NOPRODUCT);
                    }
                    sheet.setColumnWidth(i, QUARTALPROGRAMMDATENCOLUMNWIDTH);
                }
                // Month
                else {
                    boolean istWocheSummeColumn =
                        this.weekSumColumnList.contains(new Integer(i));
                    if (!istWocheSummeColumn) {
                        this.eachMonateFirstIndexVonProgrammDatenList.add(new Integer(
                            this.eachMonateFirstIndexVonProgrammDaten));

                        this.eachMonateFirstIndexVonProgrammDaten =
                            i - startProgrammDatenColumn - geschriebeneWocheSummeCellAnzahl;

                        if ((programmDaten != null) && !programmDaten.isEmpty()) {
                            Double daten =
                                programmDaten.get(i - startProgrammDatenColumn
                                                  - geschriebeneWocheSummeCellAnzahl);

                            if (isProgrammZahlen) {
                                double value = daten.doubleValue();
                                cell.setCellValue(value);
                                monatsSumme += value;
                            }
                        }
                        DayShortDTOImpl tk =
                            tagesKuerzeln.get(i - startProgrammDatenColumn
                                              - geschriebeneWocheSummeCellAnzahl);

                        // Production
                        checkTKNP(cell, tk, LegacyJUnitTesting.DEFAULTTEXT_NOPRODUCT);
                    } else {
                        cellStyle = this.decorator.getWocheSummeDatenCellStyle(getWorkBook());

                        String key = factoryOID.toString();
                        if (linieOID != null) {
                            key = key + "-" + linieOID.toString();
                        }
                        if (produktOID != null) {
                            key = key + "-" + produktOID.toString();
                        }

                        Integer index = this.factoryProductWeekSumIndex.get(key);
                        if (index == null) {
                            index = Integer.valueOf(0);
                        } else {
                            index = Integer.valueOf(index.intValue() + 1);
                        }
                        this.factoryProductWeekSumIndex.put(key, index);

                        if ((wocheSummeProgrammDaten != null) && !wocheSummeProgrammDaten
                            .isEmpty()) {
                            if (isProgrammZahlen) {
                                double value =
                                    wocheSummeProgrammDaten.get(index.intValue()).doubleValue();
                                cell.setCellValue(value);
                            }
                        }

                        cell.setCellStyle(cellStyle);

                        geschriebeneWocheSummeCellAnzahl++;
                    }

                    sheet.setColumnWidth(i, MONATPROGRAMMDATENCOLUMNWIDTH);
                }
            }
        }

        // MonatsSumme darstellen in Monats-Ansicht
        if (!modell.isQuartal()) {
            HSSFCell cell = row.createCell(endColumn);
            HSSFCellStyle cellStyle = this.decorator.getWocheSummeDatenCellStyle(getWorkBook());

            cell.setCellValue(isProgrammZahlen ? String.valueOf(monatsSumme) : "");
            cell.setCellStyle(cellStyle);
            sheet.setColumnWidth(endColumn, MONATPROGRAMMDATENCOLUMNWIDTH);
        }

        if (!this.date1ColumnList.isEmpty()) {
            int date1BeginColumn = this.date1ColumnList.get(0).intValue();
            int date1EndColumn = this.date1ColumnList.get(this.date1ColumnList.size() - 1).intValue();

            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(),
                                                       date1BeginColumn, date1EndColumn));
        }

        if (!this.dateColumnList.isEmpty()) {
            int date2BeginningColumn = this.dateColumnList.get(0).intValue();
            int date2EndColumn = this.dateColumnList.get(this.dateColumnList.size() - 1).intValue();

            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(),
                                                       date2BeginningColumn, date2EndColumn));
        }
        sheet.setColumnWidth(startProgrammDatenColumn - 1, BLANKCOLUMNWIDTH);

        String key = factoryOID.toString();
        if (linieOID != null) {
            key = key + "-" + linieOID.toString();
            if (produktOID != null) {
                key = key + "-" + produktOID.toString();
            }
        }

        if (this.eachTimeUnitEachFactoryProductCombinationEachMonthFirstIndexVonProgrammDatenMap
                .get(key) != null) {
            this.eachTimeUnitEachFactoryProductCombinationEachMonthFirstIndexVonProgrammDatenMap
                .get(key).add(new Integer(this.eachMonateFirstIndexVonProgrammDaten));

        } else {
            List<Integer> list = new ArrayList<Integer>();
            list.add(new Integer(this.eachMonateFirstIndexVonProgrammDaten));

            this.eachTimeUnitEachFactoryProductCombinationEachMonthFirstIndexVonProgrammDatenMap.put(
                key, list);
        }

        this.date1Written = false;
        this.dateWritten = false;

        return this.eachMonateFirstIndexVonProgrammDaten;
    }

    private void checkTKNP(HSSFCell cell, DayShortDTOImpl tk, String x) {

    }

    public Object getWorkBook() {
        return workBook;
    }

}

package com.zju.als.monitor.reporter;

import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;
import com.zju.als.monitor.artificialliver.dao.PressureMapper;
import com.zju.als.monitor.artificialliver.dao.PumpSpeedMapper;
import com.zju.als.monitor.artificialliver.domain.Cumulant;
import com.zju.als.monitor.artificialliver.domain.LiquidData;
import com.zju.als.monitor.artificialliver.domain.PressureData;
import com.zju.als.monitor.guardian.dao.GuardianMapper;
import com.zju.als.monitor.guardian.domain.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDifferenceRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import java.awt.Font;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/11/4.
 */
@Scope("prototype")
@org.springframework.stereotype.Component
public class Reporter extends Document {
    public static final double HIGHBLOODTHRESHHODL = 2;
    public static final double LOWBLOODTHRESHHOLD = 1;
    public static final double HIGHRATETHRESHHODL = 2;
    public static final double LOWRATETHRESHHODL = 1;
    BaseFont bfChinese;
    com.itextpdf.text.Font titleFontChinese;
    com.itextpdf.text.Font baseFontChinese;
    com.itextpdf.text.Font baseBoldFontChinese;
    com.itextpdf.text.Font baseTableFontChinese;
    String surgeryNo;
    Chapter chapter;
    Surgeryinfo surgeryinfo;
    List<Scheme> schemes;

    @Autowired
    PumpSpeedMapper pumpSpeedDataMapper;
    @Autowired
    PressureMapper pressureDataMapper;
    @Autowired
    private GuardianMapper guardianMapper;

    public static void setAntiAlias(JFreeChart chart) {
        chart.setTextAntiAlias(false);

    }

    public Reporter(){
        super(PageSize.A4, 50, 50, 50, 50);
    }

    public void init(Surgeryinfo surgeryinfo, List<Scheme> schemes) throws DocumentException, IOException {
        this.surgeryinfo = surgeryinfo;
        this.schemes = schemes;
        this.surgeryNo = surgeryinfo.getSurgeryNo();
        bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                BaseFont.NOT_EMBEDDED);
        titleFontChinese = new com.itextpdf.text.Font(bfChinese, 18,
                com.itextpdf.text.Font.BOLDITALIC, new CMYKColor(0, 255, 255,
                17));
        baseFontChinese = new com.itextpdf.text.Font(bfChinese, 10,
                com.itextpdf.text.Font.NORMAL, new CMYKColor(0, 255, 255, 255));
        baseBoldFontChinese = new com.itextpdf.text.Font(bfChinese, 10,
                com.itextpdf.text.Font.BOLD, new CMYKColor(0, 255, 255, 255));
        baseTableFontChinese = new com.itextpdf.text.Font(bfChinese, 8,
                com.itextpdf.text.Font.NORMAL, new CMYKColor(0, 255, 255, 255));
        PdfWriter.getInstance(this, new FileOutputStream("./" + surgeryNo
                + surgeryinfo.getPatientName() + surgeryinfo.getTime() + ".pdf"));
        this.open();
    }

    public InputStream getReporterPdf(Surgeryinfo surgeryinfo, List<Scheme> schemes)
            throws DocumentException, ParseException, IOException {
        this.init(surgeryinfo,schemes);
        this.publish();
        return new FileInputStream("./" + surgeryNo + surgeryinfo.getPatientName()
                + surgeryinfo.getTime() + ".pdf");
    }
    /**
     * 增加表头
     */
    public void setHeader() throws DocumentException {
        Paragraph title = new Paragraph("     浙江大学第一附属医院人工肝治疗仪器状态参数报告",
                titleFontChinese);
        chapter = new Chapter(title, 1);
        chapter.setNumberDepth(0);

        // base information
        Paragraph baseInformation = new Paragraph(
                String.format(
                        "\n               姓名 :%s                        性别 : %s                     年龄 :%s                                                   治疗方法:%s           \n               医生 :%s                   手术号 : %s                  开始时间 :%s              治疗时间：%s分钟\n",
                        surgeryinfo.getPatientName(), surgeryinfo.getGender(),
                        surgeryinfo.getAge(), surgeryinfo.getTreatMethod(),
                        surgeryinfo.getDoctor(), surgeryinfo.getSurgeryNo(),
                        surgeryinfo.getTime(), surgeryinfo.getOperationTime(),
                baseFontChinese));
        chapter.add(baseInformation);
        chapter.add(new Paragraph(
                "------------------------------------------------------------------------------------------------------------------------\n",
                baseFontChinese));
    }

    public void setCumulantParagraph() throws DocumentException, IOException {
        Cumulant cumulant = pumpSpeedDataMapper.getCumulant(surgeryNo);

        PdfPTable t = new PdfPTable(3);
        int headerwidths[] = { 2, 2, 1 }; // percentage
        t.setSpacingBefore(10f);
        t.setSpacingAfter(10f);
        t.setWidths(headerwidths);
        t.setWidthPercentage(40);
        t.setHorizontalAlignment(Element.ALIGN_LEFT);

        t.getDefaultCell().setPadding(3);
        t.getDefaultCell().setBorderWidth(10);
        t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        String[] colName = new String[] { "基本项目", "测量值", "单位" };
        for (String col : colName) {
            PdfPCell pCell = new PdfPCell(new Phrase(col, baseTableFontChinese));
            pCell.setBorderWidth(2);
            pCell.setBorderColor(BaseColor.LIGHT_GRAY);
            pCell.setGrayFill(0.95f);
            pCell.setPadding(5);
            t.addCell(pCell);
        }
        t.setHeaderRows(1);
        String[] strings = new String[] { "实际治疗时间", cumulant.getCumulative_time(),
                "Min", "血液循环量", cumulant.getBlood_pump_t(), "L", "分离量",
                cumulant.getSeparation_pump_t(), "L", "透析液量",
                cumulant.getDialysis_pump_t(), "L", "补液量", cumulant.getTripe_pump_t(),
                "L", "滤过量", cumulant.getFiltration_pump_t(), "L", "循环量",
                cumulant.getCirculating_pump_t(), "L", "肝素量",
                cumulant.getHeparin_pump_t(), "ml" };
        t.getDefaultCell().setBorderWidth(1);
        for (String string : strings) {
            PdfPCell c = new PdfPCell(new Phrase(string, baseTableFontChinese));
            c.setBorderWidth(1);
            c.setBorderColor(BaseColor.LIGHT_GRAY);
            c.setGrayFill(0.95f);
            t.addCell(c);
        }
        chapter.add(t);

        JFreeChart chart = PieChart3D.createChart(PieChart3D
                .createDataset());

        BufferedImage bufferedImage = chart.createBufferedImage(400, 250);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", out);
        byte[] bytes = out.toByteArray();
        Image img = Image.getInstance(bytes);
        img.scalePercent(50);
        img.setAbsolutePosition(300, 571);
        chapter.add(img);

        chapter.add(new Paragraph(
                "------------------------------------------------------------------------------------------------------------------------\n",
                baseFontChinese));
    }

    public void publish() throws DocumentException, ParseException, IOException {
        this.setHeader();
        this.setCumulantParagraph();
        this.setPressureParagraph();
        this.setLiquidParagraph();
        this.setHeartRateParagraph();
        this.addBloodPressureParagraph();
        this.addIllustrate();
        this.addTailer();
        this.add(chapter);
        this.close();
    }

    private void addIllustrate() {
        chapter.add(new Paragraph(
                "------------------------------------------------------------------------------------------------------------------------\n",
                baseFontChinese));
        chapter.add(new Paragraph("\n  诊断描述：\n\n", baseBoldFontChinese));
        PdfPCell pCell = new PdfPCell(new Phrase("具体诊断说明", baseTableFontChinese));
        pCell.setBorderWidth(2);
        pCell.setBorderColor(BaseColor.RED);
        pCell.setGrayFill(0.95f);
        pCell.setPadding(5);
        pCell.setFixedHeight(90);
        PdfPTable table=new PdfPTable(1);
        table.addCell(pCell);
        table.setTotalWidth(445);
        table.setLockedWidth(true);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        chapter.add(table);

    }

    public JFreeChart createChart(TimeSeriesCollection lineDataset, String title, String ylabel, String xlabel) {
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title, // 标题
                xlabel, // categoryAxisLabel （category轴，横轴，X轴的标签）
                ylabel, // valueAxisLabel（value轴，纵轴，Y轴的标签）
                lineDataset,// dataset
                true, // legend
                true, // tooltips
                false); // URLs
        // 配置字体（解决中文乱码的通用方法）
        jfreechart.getTitle().setVisible(false);
        LegendTitle legend = (LegendTitle) jfreechart.getSubtitle(0);
        legend.setPosition(RectangleEdge.BOTTOM);
        Font xfont = new Font("宋体", Font.PLAIN, 20); // X轴
        Font yfont = new Font("宋体", Font.PLAIN, 20); // Y轴
        Font kfont = new Font("宋体", Font.PLAIN, 20); // 底部
        Font titleFont = new Font("隶书", Font.BOLD, 28); // 图片标题

        jfreechart.setBackgroundPaint(new Color(200, 255, 255));
        XYPlot xyplot = (XYPlot) jfreechart.getPlot(); // 获得 plot：XYPlot！

        xyplot.getDomainAxis().setLabelFont(xfont);
        xyplot.getRangeAxis().setLabelFont(yfont);

        jfreechart.getLegend().setItemFont(kfont);
        jfreechart.getTitle().setFont(titleFont);

        // 设置时间格式，同时也解决了乱码问题
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        SimpleDateFormat sfd = new SimpleDateFormat("HH-mm");
        dateaxis.setDateFormatOverride(sfd);
        xyplot.setDomainAxis(dateaxis);

        // 以下的设置可以由用户定制，也可以省略
        XYPlot plot = (XYPlot) jfreechart.getPlot();
        XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) plot
                .getRenderer();
        // 设置网格背景颜色
        plot.setBackgroundPaint(Color.gray);
        // 设置网格竖线颜色
        plot.setDomainGridlinePaint(Color.black);
        // 设置网格横线颜色
        plot.setRangeGridlinePaint(Color.black);
        // 设置曲线图与xy轴的距离
        plot.setAxisOffset(new RectangleInsets(0D, 0D, 0D, 10D));
        // 设置曲线是否显示数据点
        // xylineandshaperenderer.setBaseShapesVisible(true);
        // 设置曲线显示各数据点的值
        XYItemRenderer xyitem = plot.getRenderer();
        // xyitem.setBaseItemLabelsVisible(true);
        xyitem.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
        xyitem.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
        xyitem.setBaseItemLabelFont(new Font("Dialog", 1, 28));
        plot.setRenderer(xyitem);
        LegendTitle legendTitle = jfreechart.getLegend();
        legendTitle.setItemFont(new Font("宋体", Font.PLAIN, 20));
        setAntiAlias(jfreechart);

        for (Scheme scheme : this.schemes) {
            double millis = scheme.startTime.getFirstMillisecond();
            Marker cooling = new ValueMarker(millis, Color.blue,
                    new BasicStroke(2.0f));
            plot.addDomainMarker(cooling, Layer.BACKGROUND);
        }

        for (int i = 0; i < schemes.size() - 1; i++) {
            double millis1 = schemes.get(i).startTime.getFirstMillisecond();
            double millis2 = schemes.get(i + 1).startTime.getFirstMillisecond();
            Marker cooling = new IntervalMarker(millis1, millis2);
            cooling.setLabelOffsetType(LengthAdjustmentType.EXPAND);
            if (i % 2 == 0)
                cooling.setPaint(new Color(255, 255, 255));
            else
                cooling.setPaint(new Color(120, 255, 255));
            cooling.setLabel(schemes.get(i).solutionName);
            cooling.setLabelFont(new Font("宋体", Font.PLAIN, 20));

            cooling.setLabelPaint(Color.blue);
            cooling.setLabelAnchor(RectangleAnchor.CENTER);
            cooling.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
            plot.addDomainMarker(cooling, Layer.BACKGROUND);
        }

        return jfreechart;
    }

    public void setLiquidParagraph() throws ParseException, IOException,DocumentException {
        List<LiquidData> liquidDatas = pumpSpeedDataMapper.getLiquidDatas(surgeryNo);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeSeriesCollection lineDataset = new TimeSeriesCollection();
        TimeSeries timeSeries1 = new TimeSeries("血液循环量", Second.class);
        TimeSeries timeSeries2 = new TimeSeries("补液量", Second.class);
        TimeSeries timeSeries3 = new TimeSeries("废液量", Second.class);
        for (LiquidData liquidData : liquidDatas) {
            Date date = dateFormat.parse(liquidData.getTime_stamp());
            Second second = new Second(date);
            timeSeries1
                    .add(second, Double.parseDouble(liquidData.getBlood_pump_t()));
            timeSeries2
                    .add(second, Double.parseDouble(liquidData.getTripe_pump_t()));
            timeSeries3.add(second,
                    Double.parseDouble(liquidData.getFiltration_pump_t()));

        }
        lineDataset.addSeries(timeSeries3);
        lineDataset.addSeries(timeSeries2);
        lineDataset.addSeries(timeSeries1);
        JFreeChart chart = createChart(lineDataset, "液体累积量变化图", "累积量", "时间");
        XYPlot plot = (XYPlot) chart.getPlot();
        XYDifferenceRenderer r = new XYDifferenceRenderer(Color.blue,
                Color.red, false);
        r.setRoundXCoordinates(true);
        plot.setRenderer(r);
        plot.setForegroundAlpha(0.5f);

        BufferedImage bufferedImage = chart.createBufferedImage(900, 500);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", out);
        byte[] bytes = out.toByteArray();
        Image img = Image.getInstance(bytes);
        img.scalePercent(25);
        img.setAbsolutePosition(280, 423);
        chapter.add(img);

    }

    public void setPressureParagraph() throws ParseException, IOException,DocumentException {
        List<PressureData> PressureDatas = pressureDataMapper.getPressureDatas(surgeryNo);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeSeriesCollection lineDataset = new TimeSeriesCollection();
        TimeSeries timeSeries1 = new TimeSeries("采血压", Second.class);
        TimeSeries timeSeries2 = new TimeSeries("血浆入口压力", Second.class);
        TimeSeries timeSeries3 = new TimeSeries("动脉压", Second.class);
        TimeSeries timeSeries4 = new TimeSeries("静脉压", Second.class);
        TimeSeries timeSeries5 = new TimeSeries("血浆压", Second.class);
        TimeSeries timeSeries6 = new TimeSeries("夸膜压", Second.class);
        for (PressureData pressureData : PressureDatas) {
            Date date = new Date(pressureData.getTime_stamp());
            Second second = new Second(date);
            timeSeries1.add(second,
                    Double.parseDouble(pressureData.getIn_blood_pressure()));
            timeSeries2.add(second,
                    Double.parseDouble(pressureData.getPlasma_inlet_pressure()));
            timeSeries3.add(second,
                    Double.parseDouble(pressureData.getArterial_pressure()));
            timeSeries4.add(second,
                    Double.parseDouble(pressureData.getVenous_pressure()));
            timeSeries5.add(second,
                    Double.parseDouble(pressureData.getPlasma_pressure()));
            timeSeries6.add(second,
                    Double.parseDouble(pressureData.getTransmembrane_pressure()));
        }
        lineDataset.addSeries(timeSeries1);
        lineDataset.addSeries(timeSeries2);
        lineDataset.addSeries(timeSeries3);
        lineDataset.addSeries(timeSeries4);
        lineDataset.addSeries(timeSeries5);
        lineDataset.addSeries(timeSeries6);
        JFreeChart chart = createChart(lineDataset, "压力变化图", "压力值", "时间");
        BufferedImage bufferedImage = chart.createBufferedImage(900, 500);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", out);
        byte[] bytes = out.toByteArray();
        Image img = Image.getInstance(bytes);
        img.scalePercent(25);
        chapter.add(img);

    }

    public void setHeartRateParagraph() throws ParseException, IOException,
            DocumentException {
        List<HeartRateData> heartRateDatas = guardianMapper.getHeartRateDatas(surgeryNo);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeSeriesCollection lineDataset = new TimeSeriesCollection();
        TimeSeries timeSeries1 = new TimeSeries("心率", Second.class);
        for (HeartRateData heartRateData : heartRateDatas) {
            Date date = dateFormat.parse(heartRateData.getTime_stamp());
            Second second = new Second(date);
            timeSeries1.add(second,
                    Double.parseDouble(heartRateData.getHeart_rate()));
        }
        lineDataset.addSeries(timeSeries1);
        JFreeChart chart = createChart(lineDataset, "心率变化图", "心率值", "时间");

        Marker lowThreshold = new ValueMarker(LOWRATETHRESHHODL);
        lowThreshold.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        lowThreshold.setPaint(Color.red);
        lowThreshold.setStroke(new BasicStroke(2.0f));
        lowThreshold.setLabel(" 下限");
        lowThreshold.setLabelFont(new Font("宋体", Font.PLAIN, 20));
        lowThreshold.setLabelPaint(Color.red);
        lowThreshold.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        lowThreshold.setLabelTextAnchor(TextAnchor.BOTTOM_LEFT);
        ((XYPlot) chart.getPlot()).addRangeMarker(lowThreshold);

        Marker highThreshold = new ValueMarker(HIGHRATETHRESHHODL);
        highThreshold.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        highThreshold.setPaint(Color.red);
        highThreshold.setStroke(new BasicStroke(2.0f));
        highThreshold.setLabel("上限");
        highThreshold.setLabelFont(new Font("宋体", Font.PLAIN, 20));
        highThreshold.setLabelPaint(Color.red);
        highThreshold.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        highThreshold.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        ((XYPlot) chart.getPlot()).addRangeMarker(highThreshold);

        BufferedImage bufferedImage = chart.createBufferedImage(900, 500);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", out);
        byte[] bytes = out.toByteArray();
        Image img = Image.getInstance(bytes);
        img.scalePercent(25);
        chapter.add(img);

    }

    public void addBloodPressureParagraph() throws ParseException, IOException,DocumentException {
        List<BloodPressureData> bloodPressureDatas = guardianMapper.getBloodPressureDatas(surgeryNo);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeSeriesCollection lineDataset = new TimeSeriesCollection();
        TimeSeries timeSeries1 = new TimeSeries("舒张压", Second.class);
        TimeSeries timeSeries2 = new TimeSeries("收缩压", Second.class);
        for (BloodPressureData bloodPressureData : bloodPressureDatas) {
            Date date = dateFormat.parse(bloodPressureData.getTime_stamp());
            Second second = new Second(date);
            timeSeries1.add(second,
                    Double.parseDouble(bloodPressureData.getDiastolic_pressure()));
            timeSeries2.add(second,
                    Double.parseDouble(bloodPressureData.getSystolic_pressure()));
        }
        lineDataset.addSeries(timeSeries1);
        lineDataset.addSeries(timeSeries2);
        JFreeChart chart = createChart(lineDataset, "血压变化图", "血压值", "时间");

        Marker lowThreshold = new ValueMarker(LOWBLOODTHRESHHOLD);
        lowThreshold.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        lowThreshold.setPaint(Color.red);
        lowThreshold.setStroke(new BasicStroke(2.0f));
        lowThreshold.setLabel(" 下限");
        lowThreshold.setLabelFont(new Font("宋体", Font.PLAIN, 20));
        lowThreshold.setLabelPaint(Color.red);
        lowThreshold.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        lowThreshold.setLabelTextAnchor(TextAnchor.BOTTOM_LEFT);
        ((XYPlot) chart.getPlot()).addRangeMarker(lowThreshold);

        Marker highThreshold = new ValueMarker(HIGHBLOODTHRESHHODL);
        highThreshold.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        highThreshold.setPaint(Color.red);
        highThreshold.setStroke(new BasicStroke(2.0f));
        highThreshold.setLabel("上限");
        highThreshold.setLabelFont(new Font("宋体", Font.PLAIN, 20));
        highThreshold.setLabelPaint(Color.red);
        highThreshold.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        highThreshold.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        ((XYPlot) chart.getPlot()).addRangeMarker(highThreshold);
        BufferedImage bufferedImage = chart.createBufferedImage(900, 500);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", out);
        byte[] bytes = out.toByteArray();
        Image img = Image.getInstance(bytes);
        img.scalePercent(25);
        img.setAbsolutePosition(280, 291);
        chapter.add(img);

    }

    public void addTailer() {
        chapter.add(new Paragraph(
                " \n                                                                                      操作人签名 :                               审核人签名 :                  \n",
                baseFontChinese));
        chapter.add(new Paragraph("\n      备注： ", baseFontChinese));
    }

    public static void main(String[] args) throws DocumentException,
            IOException, ParseException {
        List<Scheme> schemes = new ArrayList<Scheme>();
        schemes.add(new Scheme("阶段1", "2015-03-04 11:39:20 "));
        schemes.add(new Scheme("阶段2", "2015-03-04 11:39:30 "));
        schemes.add(new Scheme("stop ", "2015-03-04 11:39:38"));

        Reporter report = new Reporter();
        report.init(new Surgeryinfo("张三", "男", "20",
                "方法一", "王医生", "1", "2015-03-08 12:00", "120"), schemes);
        report.publish();
        System.out.println("finished");

    }
}

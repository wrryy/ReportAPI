package pl.wojrydz.softwareplant.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.wojrydz.softwareplant.api.model.PutRequest;

@RestController
@RequestMapping(("/report"))
public class ReportController {
    @Autowired
    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity getAllReports() {
        return reportService.getAll();
    }

    @GetMapping("/{report_id")
    public ResponseEntity<Report> getReport(@PathVariable long report_id) {
        return reportService.getOne(report_id);
    }

    @PutMapping(value = "/{report_id}", headers = "content-type=application/json")
    public ResponseEntity putReport(@PathVariable("report_id") long report_id,
            @RequestBody PutRequest putRequest) {
        return reportService.putReport(report_id, putRequest);
    }

    @DeleteMapping
    public ResponseEntity deleteAllReports() {
        return reportService.deleteAll();
    }

    @DeleteMapping("/{report_id")
    public ResponseEntity deleteReport(@PathVariable long report_id) {
        return reportService.deleteOne(report_id);
    }

}

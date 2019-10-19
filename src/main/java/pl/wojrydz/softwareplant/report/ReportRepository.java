package pl.wojrydz.softwareplant.report;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findByReportId(long report_id);
    Report deleteByReportId(long report_id);
}

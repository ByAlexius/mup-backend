package at.rziha.mup.entity;

import at.rziha.mup.model.MeetingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "meeting")
@Getter
@Setter
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private Account creator;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MeetingStatus status;

    @OneToOne
    @JoinColumn(name = "final_meeting_date_id")
    private MeetingDate finalMeetingDate;

    private String location;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<MeetingDate> meetingDates;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<Participant> participants;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}

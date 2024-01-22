import java.util.ArrayList;
import java.util.List;

public class Member {
    private static List<Member> members = new ArrayList<>();

    private int memberId;
    private String name;
    private String contactInfo;
    private List<Book> booksBorrowed;

    public Member(int memberId, String name, String contactInfo) {
        this.memberId = memberId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.booksBorrowed = new ArrayList<>();
        members.add(this);
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }
    public static Member findMemberById(int memberId) {
        return MemberRepository.findMemberById(memberId);
    }

}

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    private static List<Member> members = new ArrayList<>();
    public static Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }
}

package apoc.date;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.procedure.Description;
import org.neo4j.procedure.Mode;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.Procedure;

import java.util.concurrent.TimeUnit;

import static apoc.date.Date.unit;


/**
 * @author tkroman
 * @since 9.04.2016
 */
public class DateExpiry {
	@Procedure(mode = Mode.WRITE, deprecatedBy = "apoc.ttl.expireAtInstant")
	@Description("CALL apoc.date.expire(node,time,'time-unit') - expire node at specified time by setting :TTL label and `ttl` property")
	@Deprecated
	public void expire(@Name("node") Node node, @Name("time") long time, @Name("timeUnit") String timeUnit) {
		node.addLabel(Label.label("TTL"));
		node.setProperty("ttl",unit(timeUnit).toMillis(time));
	}

	@Procedure(mode = Mode.WRITE, deprecatedBy = "apoc.ttl.expireAfterTimeLength")
	@Description("CALL apoc.date.expire.in(node,time,'time-unit') - expire node after specified length of time time by setting :TTL label and `ttl` property")
	@Deprecated
	public void expireIn(@Name("node") Node node, @Name("timeDelta") long time, @Name("timeUnit") String timeUnit) {
		node.addLabel(Label.label("TTL"));
		node.setProperty("ttl",System.currentTimeMillis() + unit(timeUnit).toMillis(time));
	}
}
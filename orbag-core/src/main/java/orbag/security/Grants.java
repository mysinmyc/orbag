package orbag.security;

import java.util.HashSet;
import java.util.Set;

public class Grants {

	
	public static Grants all() {
		Grants result = new Grants();
		result.addAll();
		return result;
	}
	
	
	boolean grants[] = new boolean[AccessType.values().length];

	boolean explicit = true;

	public boolean isExplicit() {
		return explicit;
	}

	public void setExplicit(boolean explicit) {
		this.explicit = explicit;
	}

	public void add(AccessType... grantsToAdd) {
		for (AccessType grantToAdd : grantsToAdd) {
			grants[grantToAdd.ordinal()] = true;
		}
	}
		
	public void addAll() {
		for (int cnt=0;cnt<grants.length;cnt++) {
			grants[cnt] = (cnt != AccessType.DENIED.ordinal());
		}
	}
	
	public void revoke(AccessType... grantsToRevoke) {
		for (AccessType grantToRevoke : grantsToRevoke) {
			grants[grantToRevoke.ordinal()] = false;
		}
	}
		
	public boolean hasAccess(AccessType accessType) {
		return grants[accessType.ordinal()]  && ! grants[AccessType.DENIED.ordinal()];
	}
	
	public boolean hasAnyAccess(AccessType... accessTypes) {
		if (accessTypes.length==0) {
			throw new UnsupportedOperationException("Wrong security test");
		}
		for (AccessType accessType : accessTypes) {
			if (hasAccess(accessType)) {
				return true;
			}
		}
		return false;
	}
	
	Set<String> sources =null;
	
	public boolean addSource(String source) {
		if (sources==null) {
			sources=new HashSet<String>();
		}
		return sources.add(source);
	}
}

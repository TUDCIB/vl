//package de.tudresden.bau.cib.vl.gui.common.widgets.video;
//
//import org.eclipse.rap.rwt.RWT;
//import org.eclipse.rap.rwt.SingletonUtil;
//
//
//
//public class HostInfo {
//
//	private String host;
//	private int port;
//	private String context;
//	private String scheme;
//	
//
//	public static HostInfo getInstance() {
//		return SingletonUtil.getSessionInstance(HostInfo.class);
//	}
//
//    public String getHost() {
//        return this.host;
//    }
//
//    public int getPort() {
//        return this.port;
//    }
//
//    public String getContext() {
//        return this.context;
//    }
//
//	protected void doSetHost(final String host) {
//		this.host = host;
//	}
//
//	protected void doSetPort(final int port) {
//		this.port = port;
//	}
//
//	protected void doSetScheme(final String scheme) {
//		this.scheme = scheme;
//	}
//
//	protected void doSetContext(final String context) {
//		this.context = context;
//	}
//
//	public void init() {
//		doSetHost(RWT.getRequest().getServerName());
//		doSetPort(RWT.getRequest().getLocalPort());
//		doSetContext(RWT.getRequest().getContextPath());
//		doSetScheme(RWT.getRequest().getScheme());
//	}
//}

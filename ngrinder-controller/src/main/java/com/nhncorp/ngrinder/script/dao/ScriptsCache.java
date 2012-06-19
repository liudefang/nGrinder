package com.nhncorp.ngrinder.script.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nhncorp.ngrinder.script.model.Script;

/**
 * scripts cache
 * 
 * @author Liu Zhifei
 * @date 2012-6-14
 */
public final class ScriptsCache {

	private ScriptsCache() {
	}

	private static ScriptsCache INSTANCE = new ScriptsCache();

	public static ScriptsCache getInstance() {
		return INSTANCE;
	}

	// / CopyOnWriteArrayList / ConcurrentHashMap
	private final Map<Long, Script> scriptsMap = new HashMap<Long, Script>();

	private final List<Script> scriptsList = new ArrayList<Script>();

	// private final ThreadLocal<List<Script>> scripts = new
	// ThreadLocal<List<Script>>();

	public void put(long id, Script script) {
		scriptsMap.put(id, script);

		scriptsList.remove(script);
		scriptsList.add(script);
	}

	public Script get(long id) {
		return scriptsMap.get(id);
	}

	public List<Script> get() {
		return new ArrayList<Script>(scriptsList);
	}

	public void remove(long id) {
		scriptsList.remove(scriptsMap.remove(id));
	}
}

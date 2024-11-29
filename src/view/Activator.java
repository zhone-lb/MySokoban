package view;

/**
 * 用于激活因Serializable失效的语句，如子组件无法定位到父容器，Listener失效等 <p>
 * 注意执行一个组件的activate()之前，先执行父容器的activate()，再添加该组件到父容器，再执行该activate()
 */
public interface Activator {
    void activate();
}
